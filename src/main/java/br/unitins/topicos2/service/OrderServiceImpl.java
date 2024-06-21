package br.unitins.topicos2.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.unitins.topicos2.dto.ItemOrderDTO;
import br.unitins.topicos2.dto.OrderDTO;
import br.unitins.topicos2.dto.OrderResponseDTO;
import br.unitins.topicos2.model.Address;
import br.unitins.topicos2.model.City;
import br.unitins.topicos2.model.ItemOrder;
import br.unitins.topicos2.model.Manga;
import br.unitins.topicos2.model.Order;
import br.unitins.topicos2.model.Payment;
import br.unitins.topicos2.model.PaymentStatus;
import br.unitins.topicos2.model.User;
import br.unitins.topicos2.repository.CityRepository;
import br.unitins.topicos2.repository.MangaRepository;
import br.unitins.topicos2.repository.OrderRepository;
import br.unitins.topicos2.repository.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import br.unitins.topicos2.validation.ValidationException;

@ApplicationScoped
public class OrderServiceImpl implements OrderService {

    @Inject
    MangaRepository mangaRepository;

    @Inject
    MangaService mangaService;

    @Inject
    UserRepository userRepository;

    @Inject
    OrderRepository orderRepository;

    @Inject
    CityRepository cityRepository;

    @Override
    @Transactional
    public OrderResponseDTO insert(OrderDTO dto, String email) {

        if (dto.itens().isEmpty() || dto == null || dto.itens() == null) {
            throw new ValidationException("400", "Não há produtos na compra");
        }
        User user = userRepository.findByEmail(email);

        Order pedido = new Order();
        pedido.setDataHora(new Date());

        Double total = 0.0;

        for (ItemOrderDTO itemDto : dto.itens()) {
            total += (mangaRepository.findById(itemDto.idManga()).getPrice() * itemDto.quantity());
        }
        pedido.setTotalOrder(total);

        pedido.setItens(new ArrayList<ItemOrder>());
        for (ItemOrderDTO itemDto : dto.itens()) {
            if (itemDto.quantity() > mangaService.findById(itemDto.idManga()).inventory()) {
                throw new ValidationException("400", "Estoque insuficiente");
            }
            ItemOrder item = new ItemOrder();
            item.setPrice(mangaRepository.findById(itemDto.idManga()).getPrice());
            item.setQuantity(itemDto.quantity());
            item.setOrder(pedido);
            Manga manga = mangaRepository.findById(itemDto.idManga());
            item.setManga(manga);

            manga.setInventory(manga.getInventory() - item.getQuantity());

            pedido.getItens().add(item);
        }

        if (dto.payment() != null) {
            Payment payment = new Payment();
            payment.setCardNumber(dto.payment().cardNumber());
            payment.setCardHolderName(dto.payment().cardHolderName());
            payment.setCardExpiration(dto.payment().cardExpiration());
            payment.setCardCvv(dto.payment().cardCvv());
            payment.setAmount(total);
            payment.setOrder(pedido);
            pedido.setPayment(payment);

            // Aqui você pode adicionar a lógica para processar o pagamento
            // e definir o status do pagamento com base no resultado do processamento.
            // Vamos supor que o pagamento foi processado com sucesso.
            pedido.setPaymentStatus(PaymentStatus.COMPLETED);
        } else {
            pedido.setPaymentStatus(PaymentStatus.PENDING);
            //throw new ValidationException("400", "Detalhes de pagamento não fornecidos");
        }

        if (dto.address() != null) {
            Address address = new Address();
            address.setName(dto.address().name());
            address.setPostalCode(dto.address().postalCode());
            address.setAddress(dto.address().address());
            address.setComplement(dto.address().complement());
            City city = cityRepository.findById(dto.address().city());
            address.setCity(city);
            pedido.setAddress(address);
        } else {
            throw new ValidationException("400", "Endereço de entrega não fornecido");
        }

        pedido.setUser(user);

        orderRepository.persist(pedido);

        return OrderResponseDTO.valueOf(pedido);
    }

    @Override
    public OrderResponseDTO findByOrderId(Long id) {
        return OrderResponseDTO.valueOf(orderRepository.findById(id));
    }

    @Override
    public List<OrderResponseDTO> findByUserId(Long id) {
        User user = userRepository.findById(id);
        return orderRepository.findAll(user.getId()).stream().map(e -> OrderResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<OrderResponseDTO> findByLogin(String login) {
        return orderRepository.findByLogin(login).stream()
            .map(e -> OrderResponseDTO.valueOf(e)).toList();
    }

    public List<OrderResponseDTO> findAll() {
        return orderRepository.listAll().stream().map(e -> OrderResponseDTO.valueOf(e)).toList();
    }
    
}
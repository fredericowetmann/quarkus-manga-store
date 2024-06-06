package br.unitins.topicos2.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.unitins.topicos2.dto.GenreResponseDTO;
import br.unitins.topicos2.dto.ItemOrderDTO;
import br.unitins.topicos2.dto.OrderDTO;
import br.unitins.topicos2.dto.OrderResponseDTO;
import br.unitins.topicos2.dto.UserResponseDTO;
import br.unitins.topicos2.model.ItemOrder;
import br.unitins.topicos2.model.Manga;
import br.unitins.topicos2.model.Order;
import br.unitins.topicos2.model.User;
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

    // @Inject
    // PixRepository pixRepository;

    // @Inject
    // CreditCardRepository creditCardRepository;

    @Override
    @Transactional
    public OrderResponseDTO insert(OrderDTO dto, String email) {

        if(dto.itens().isEmpty() || dto == null || dto.itens() == null){
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
            if(itemDto.quantity() > mangaService.findById(itemDto.idManga()).inventory()) {
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

        //pedido.setPayment(new Payment(total));

        pedido.setUser(user);

        orderRepository.persist(pedido);

        return OrderResponseDTO.valueOf(pedido); 
    }
    

    // @Override
    // public void finishPayment(Long idOrder) throws NullPointerException {

    //     Order order = orderRepository.findById(idOrder);

    //     order.setDataCompra(LocalDateTime.now());

    //     // order.setAdress(order.getUser().getAddresses());

    //     order.setIfConcluida(true);
    // }

    // @Override
    // @Transactional
    // public void payUsingPix(String email) {
    //     User user = userRepository.findByEmail(email);

        
    //     Order order = validar(user);

    //     Pix payment = new Pix(order.getTotalOrder(), order.getUser().getName(), order.getUser().getCpf());

    //     pixRepository.persist(payment);

    //     order.setPayment(payment);

    //     if (order.getPayment() == null)
    //         throw new NullPointerException("Não foi efetuado nenhum pagamento");

    //     order.getPayment().setConfirmationPayment(true);
    //     order.setIfFinished(true);
    // }

    // @Override
    // @Transactional
    // public void payUsingCreditCard(String email, CreditCardDTO creditCardDTO) {
    //     User user = userRepository.findByEmail(email);

    //     Order order = validar(user);

    //     CreditCard payment = new CreditCard(order.getTotalOrder(),
    //                                         creditCardDTO.cardNumber(),
    //                                         creditCardDTO.impressedCardName(),
    //                                         user.getCpf(),
    //                                         CardBrand.valueOf(creditCardDTO.cardBrand()));
        
    //     creditCardRepository.persist(payment);

    //     order.setPayment(payment);

    //     if (order.getPayment() == null)
    //         throw new NullPointerException("Não foi efetuado nenhum pagamento");

    //     order.getPayment().setConfirmationPayment(true);
    //     order.setIfFinished(true);
    // }

    // private Order validar(User user) throws NullPointerException {

    //     Order order = orderRepository.findByUserWhereIsNotFinished(user);

    //     if (order == null)
    //         throw new NullPointerException("Não há nenhuma compra em andamento");

    //     if (order.getItens().size() == 0)
    //         throw new NullPointerException("Não há nenhum item no pedido");

    //     return order;
    // }

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
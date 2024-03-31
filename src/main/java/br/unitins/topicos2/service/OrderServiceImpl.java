package br.unitins.topicos2.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.unitins.topicos2.dto.ItemOrderDTO;
import br.unitins.topicos2.dto.OrderDTO;
import br.unitins.topicos2.dto.OrderResponseDTO;
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
    public OrderResponseDTO insert(OrderDTO dto, String login) {

        if(dto.itens().isEmpty() || dto == null || dto.itens() == null){
            throw new ValidationException("400", "Não há produtos na compra");
        }
        User user = userRepository.findByLogin(login);

        /* if(user.getPhysicalPerson() == null || user.getPhysicalPerson().getCpf() == null || user.getPhysicalPerson().getName() == null){
            throw new ValidationException("400", "Seu perfil de usuario não está completo");
        } */

        Order pedido = new Order();
        pedido.setDataHora(LocalDateTime.now());

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

        pedido.setUser(userRepository.findByLogin(login));

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
    // public void payUsingPix(String login) {
    //     User user = userRepository.findByEmail(login);

        
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
    // public void payUsingCreditCard(String login, CreditCardDTO creditCardDTO) {
    //     User user = userRepository.findByEmail(login);

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
    public OrderResponseDTO findById(Long id) {
        return OrderResponseDTO.valueOf(orderRepository.findById(id));
    }

    @Override
    public List<OrderResponseDTO> getAll(int page, int pageSize) {
        List<Order> list = orderRepository
                                .findAll()
                                .page(page, pageSize)
                                .list();
        
        return list.stream().map(e -> OrderResponseDTO.valueOf(e)).collect(Collectors.toList());
    }  

    @Override
    public List<OrderResponseDTO> findByAll(String login) {
        return orderRepository.listAll().stream()
            .map(e -> OrderResponseDTO.valueOf(e)).toList();
    }
    
}
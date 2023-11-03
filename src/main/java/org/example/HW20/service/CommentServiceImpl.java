package org.example.HW20.service;

import org.example.HW20.entity.Comments;
import org.example.HW20.entity.Customer;
import org.example.HW20.entity.Expert;
import org.example.HW20.entity.Orders;
import org.example.HW20.entity.enumeration.ExpertStatus;
import org.example.HW20.exceptions.user.UserInActiveException;
import org.example.HW20.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final ExpertServiceImpl expertService;
    private final OrderServiceImpl orderService;
    private final CustomerServiceImpl customerService;

    @Override
    public Comments create(Comments comments) {
        Customer customer = customerService.findByEmail(comments.getEmail());
        Orders orders = orderService.findPaidOrder(comments.getOrders().getId(), customer.getId());
        comments.setExpert(orders.getOfferSelected().getExpert());
        expertService.updateExpertScore(comments.getScore(), orders.getOfferSelected().getExpert());
        return commentRepository.save(comments);
    }

    @Override
    public List<Comments> showExpertScore(Comments comments) {
        Expert expert = expertService.findByEmail(comments.getEmail());
        if (expert.getStatus() == ExpertStatus.INACTIVE) {
            throw new UserInActiveException("your account has been deactivated.");
        }
        return commentRepository.findExpertComment(expert.getId());
    }
}

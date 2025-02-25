package com.enigma.loanapp.specification;

import com.enigma.loanapp.entity.Customer;
import com.enigma.loanapp.model.request.SearchCustomerRequest;
import com.enigma.loanapp.util.DateUtil;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomerSpecification {
    public static Specification<Customer> getSpecification(SearchCustomerRequest request){
        return (root,cq,cb) ->{

            List<Predicate> predicates = new ArrayList<>();

            if (request.getName() != null){
                Predicate namePredicate = cb.like(cb.lower(root.get("name")), "%" + request.getName().toLowerCase() +" %");
                predicates.add(namePredicate);
            }

            if (request.getMobilePhoneNUmber() != null){
                Predicate phonePredicate = cb.equal(cb.lower(root.get("mobilePhone")), request.getMobilePhoneNUmber());
                predicates.add(phonePredicate);
            }

            if (request.getStatus() != null){
                Predicate statusPredicate = cb.equal(cb.lower(root.get("status")), request.getStatus());
                predicates.add(statusPredicate);
            }

            if (request.getBirthDate() != null){
                Date tempDate = DateUtil.parseDate(request.getBirthDate(), "yyyy-MM-dd");
                Predicate datePredicate = cb.equal(root.get("birthDate") , tempDate);
                predicates.add(datePredicate);
            }

            assert cq !=null;
            return cq.where(predicates.toArray(new Predicate[]{})).getRestriction();

        };

    }
}

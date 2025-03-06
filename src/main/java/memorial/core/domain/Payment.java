package memorial.core.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import memorial.core.common.enums.ProductCode;
import memorial.core.domain.member.Member;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
public class Payment extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    private BigDecimal price;

    private String orderId;

    private ProductCode productCode;

}
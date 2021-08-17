package br.com.rchlo.store.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal value;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @Embedded
    @AttributeOverride(name = "clientName", column = @Column(name = "card_client_name"))
    @AttributeOverride(name = "number", column = @Column(name = "card_number"))
    @AttributeOverride(name = "expiration", column = @Column(name = "card_expiration"))
    @AttributeOverride(name = "verificationCode", column = @Column(name = "card_verification_code"))
    private Card card;

    /** @deprecated */
    protected Payment() {
    }

    public Payment(BigDecimal value, Card card) {
        this.value = value;
        this.card = card;
        this.status = PaymentStatus.CREATED;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getValue() {
        return value;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void confirm() {
        checkIfCanBeConfirmed();
        this.status = PaymentStatus.CONFIRMED;
    }

    public void cancel() {
        checkIfCanBeCanceled();
        this.status = PaymentStatus.CANCELED;
    }

    private void checkIfCanBeConfirmed() {
        if (!canConfirm()) {
            throw new IllegalStatusException("Can not confirm a payment that is already canceled.");
        }
    }

    private void checkIfCanBeCanceled() {
        if (!canCancel()) {
            throw new IllegalStatusException("Can not cancel a payment that is already confirmed.");
        }
    }

    private boolean canConfirm() {
        return List.of(PaymentStatus.CREATED, PaymentStatus.CONFIRMED).contains(this.status);
    }

    private boolean canCancel() {
        return List.of(PaymentStatus.CREATED, PaymentStatus.CANCELED).contains(this.status);
    }

    public static class IllegalStatusException extends IllegalStateException {
        public IllegalStatusException(String message) {
            super(message);
        }
    }

}

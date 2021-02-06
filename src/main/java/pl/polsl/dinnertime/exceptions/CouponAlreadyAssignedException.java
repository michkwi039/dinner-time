package pl.polsl.dinnertime.exceptions;

public class CouponAlreadyAssignedException extends RuntimeException {

    public CouponAlreadyAssignedException() {
        super();
    }

    @Override
    public String getMessage() {
        return MessageProvider.getString("user.coupon.negative");
    }
}

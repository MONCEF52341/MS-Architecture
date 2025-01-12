package emsi.moncef.order.dtos;

public record OrderLineDTO(
        String skuCode,
        Long quantity,
        Long orderId
) {
}

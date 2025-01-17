export interface OrderLine {
  skuCode: string
  quantity: number
  orderId: number
}

export interface Order {
  orderNumber: string
  orderDate: string
  customerName: string
  customerEmail: string
  shippingAddress: string
  status: 'PENDING' | 'CONFIRMED' | 'SHIPPED' | 'DELIVERED' | 'REJECTED'
  orderLines: OrderLine[]
}


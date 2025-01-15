import React, { useState, useEffect } from 'react'

function OrderCards() {
  const [orders, setOrders] = useState([])
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState(null)

  useEffect(() => {
    fetch('http://localhost:9000/api/orders')
      .then(response => {
        if (!response.ok) {
          throw new Error('Failed to fetch orders')
        }
        return response.json()
      })
      .then(data => {
        setOrders(data)
        setLoading(false)
      })
      .catch(error => {
        setError(error.message)
        setLoading(false)
      })
  }, [])

  if (loading) return <div>Loading orders...</div>
  if (error) return <div>Error: {error}</div>

  return (
    <div className="order-cards">
      <h2>Order Cards</h2>
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
        {orders.map(order => (
          <div key={order.orderNumber} className="bg-white shadow-md rounded-lg p-4">
            <h3 className="text-lg font-semibold">Order: {order.orderNumber}</h3>
            <p>Customer: {order.customerName}</p>
            <p>Email: {order.customerEmail}</p>
            <p>Date: {new Date(order.orderDate).toLocaleString()}</p>
            <p>Status: {order.status}</p>
            <p>Items: {order.orderLines.length}</p>
            <p>Shipping Address: {order.shippingAddress}</p>
            <div>
              <h4>Order Lines:</h4>
              <ul>
                {order.orderLines.map((line, index) => (
                  <li key={index}>
                    SKU: {line.skuCode}, Quantity: {line.quantity}
                  </li>
                ))}
              </ul>
            </div>
          </div>
        ))}
      </div>
    </div>
  )
}

export default OrderCards


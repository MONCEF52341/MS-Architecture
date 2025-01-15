import React, { useState, useEffect } from 'react'

function OrderList() {
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
    <div className="order-list">
      <h2>Order List</h2>
      <table className="w-full">
        <thead>
          <tr>
            <th>Order Number</th>
            <th>Customer Name</th>
            <th>Order Date</th>
            <th>Status</th>
            <th>Items</th>
          </tr>
        </thead>
        <tbody>
          {orders.map(order => (
            <tr key={order.orderNumber}>
              <td>{order.orderNumber}</td>
              <td>{order.customerName}</td>
              <td>{new Date(order.orderDate).toLocaleString()}</td>
              <td>{order.status}</td>
              <td>{order.orderLines.length}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  )
}

export default OrderList


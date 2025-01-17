'use client'

import { useEffect, useState } from 'react'
import OrderCard from './OrderCard'
import { Order } from '@/types/order'

export default function OrderList() {
  const [orders, setOrders] = useState<Order[]>([])

  useEffect(() => {
    async function fetchOrders() {
      try {
        const response = await fetch('http://localhost:9000/api/orders')
        const data = await response.json()
        setOrders(data)
      } catch (error) {
        console.error('Erreur lors de la récupération des commandes:', error)
      }
    }

    fetchOrders()
  }, [])

  return (
    <div className="space-y-6">
      {orders.map((order) => (
        <OrderCard key={order.orderNumber} order={order} />
      ))}
    </div>
  )
}


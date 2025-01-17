'use client'

import { useEffect, useState } from 'react'
import ProductCard from './ProductCard'
import { Product } from '@/types/product'

export default function ProductList() {
  const [products, setProducts] = useState<Product[]>([])

  useEffect(() => {
    async function fetchProducts() {
      try {
        const response = await fetch('http://localhost:9000/api/products')
        const data = await response.json()
        setProducts(data)
      } catch (error) {
        console.error('Erreur lors de la récupération des produits:', error)
      }
    }

    fetchProducts()
  }, [])

  return (
    <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
      {products.map((product) => (
        <ProductCard key={product.skuCode} product={product} />
      ))}
    </div>
  )
}


import React, { useEffect, useState } from 'react'
import ProductCard from './ProductCard'

function ProductList() {
  const [products, setProducts] = useState([])
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState(null)

  useEffect(() => {
    fetch('http://localhost:9000/api/products')
      .then(response => {
        if (!response.ok) {
          throw new Error('Erreur réseau lors de la récupération des produits')
        }
        return response.json()
      })
      .then(data => {
        setProducts(data)
        setLoading(false)
      })
      .catch(error => {
        setError(error.message)
        setLoading(false)
      })
  }, [])

  if (loading) return <div>Chargement...</div>
  if (error) return <div>Erreur : {error}</div>

  return (
    <div className="product-list">
      {products.map(product => (
        <ProductCard key={product.skuCode} product={product} />
      ))}
    </div>
  )
}

export default ProductList


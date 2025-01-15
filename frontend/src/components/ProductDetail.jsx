import React, { useState, useEffect } from 'react'
import { useParams } from 'react-router-dom'

function ProductDetail() {
  const [product, setProduct] = useState(null)
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState(null)
  const { skuCode } = useParams()

  useEffect(() => {
    fetch(`http://localhost:9000/api/products/${skuCode}`)
      .then(response => {
        if (!response.ok) {
          throw new Error('Produit non trouvé')
        }
        return response.json()
      })
      .then(data => {
        setProduct(data)
        setLoading(false)
      })
      .catch(error => {
        setError(error.message)
        setLoading(false)
      })
  }, [skuCode])

  if (loading) return <div>Chargement...</div>
  if (error) return <div>Erreur : {error}</div>
  if (!product) return <div>Produit non trouvé</div>

  return (
    <div className="product-detail">
      <h2>{product.name}</h2>
      <img 
        src={`https://picsum.photos/seed/${product.skuCode}/300/300`}
        alt={product.name}
        className="product-image"
      />
      <p>Code SKU : {product.skuCode}</p>
      <p>Description : {product.description}</p>
      <p>Prix : {product.price.toFixed(2)} €</p>
      <p>Quantité en stock : {product.stockQuantity}</p>
      <p>Catégorie : {product.category}</p>
      <p>Fabricant : {product.manufacturer}</p>
      <p>Disponible : {product.isAvailable ? 'Oui' : 'Non'}</p>
    </div>
  )
}

export default ProductDetail


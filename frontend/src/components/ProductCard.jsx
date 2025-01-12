import React from 'react'

function ProductCard({ product }) {
  return (
    <div className="product-card">
      <h2>{product.name}</h2>
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

export default ProductCard


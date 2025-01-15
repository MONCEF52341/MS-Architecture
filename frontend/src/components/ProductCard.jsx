import React from 'react';

function ProductCard({ product }) {
  // Générer un ID aléatoire pour l'image entre 1 et 1000
  const randomImageId = Math.floor(Math.random() * 1000) + 1;

  return (
    <div className="product-card">
      <img 
        src={`https://picsum.photos/id/${randomImageId}/200/200`} 
        alt={product.name}
        className="product-image"
      />
      <h2>{product.name}</h2>
      <p>Code SKU : {product.skuCode}</p>
      <p>Description : {product.description}</p>
      <p>Prix : {product.price.toFixed(2)} Dh</p>
      <p>Quantité en stock : {product.stockQuantity}</p>
      <p>Catégorie : {product.category}</p>
      <p>Fabricant : {product.manufacturer}</p>
      <p>Disponible : {product.isAvailable ? 'Oui' : 'Non'}</p>
    </div>
  )
}

export default ProductCard


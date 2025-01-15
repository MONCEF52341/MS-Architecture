import React, { useState } from 'react'
import { useNavigate } from 'react-router-dom'

function ProductSearch() {
  const [skuCode, setSkuCode] = useState('')
  const navigate = useNavigate()

  const handleSubmit = (e) => {
    e.preventDefault()
    if (skuCode) {
      navigate(`/product/${skuCode}`)
    }
  }

  return (
    <div className="product-search">
      <h2>Rechercher un produit par SKU</h2>
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          value={skuCode}
          onChange={(e) => setSkuCode(e.target.value)}
          placeholder="Entrez le code SKU"
          required
        />
        <button type="submit">Rechercher</button>
      </form>
    </div>
  )
}

export default ProductSearch


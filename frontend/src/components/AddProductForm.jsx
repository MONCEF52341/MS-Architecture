import React, { useState } from 'react'
import { useNavigate } from 'react-router-dom'

function AddProductForm() {
  const navigate = useNavigate()
  const [formData, setFormData] = useState({
    skuCode: '',
    name: '',
    description: '',
    price: '',
    stockQuantity: '',
    category: '',
    manufacturer: '',
    isAvailable: true
  })
  const [error, setError] = useState(null)

  const handleChange = (e) => {
    const { name, value, type, checked } = e.target
    setFormData(prevData => ({
      ...prevData,
      [name]: type === 'checkbox' ? checked : value
    }))
  }

  const handleSubmit = async (e) => {
    e.preventDefault()
    try {
      const response = await fetch('http://localhost:9000/api/products', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          ...formData,
          price: parseFloat(formData.price),
          stockQuantity: parseInt(formData.stockQuantity),
        }),
      })

      if (!response.ok) {
        throw new Error('Erreur lors de l\'ajout du produit')
      }

      const result = await response.json()
      navigate(`/product/${result.skuCode}`)
    } catch (err) {
      setError(err.message)
    }
  }

  return (
    <div className="add-product-form">
      <h2>Ajouter un nouveau produit</h2>
      {error && <div className="error">{error}</div>}
      <form onSubmit={handleSubmit}>
        <div>
          <label htmlFor="skuCode">Code SKU:</label>
          <input
            type="text"
            id="skuCode"
            name="skuCode"
            value={formData.skuCode}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label htmlFor="name">Nom:</label>
          <input
            type="text"
            id="name"
            name="name"
            value={formData.name}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label htmlFor="description">Description:</label>
          <textarea
            id="description"
            name="description"
            value={formData.description}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label htmlFor="price">Prix:</label>
          <input
            type="number"
            id="price"
            name="price"
            value={formData.price}
            onChange={handleChange}
            step="0.01"
            required
          />
        </div>
        <div>
          <label htmlFor="stockQuantity">Quantité en stock:</label>
          <input
            type="number"
            id="stockQuantity"
            name="stockQuantity"
            value={formData.stockQuantity}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label htmlFor="category">Catégorie:</label>
          <select
            id="category"
            name="category"
            value={formData.category}
            onChange={handleChange}
            required
          >
            <option value="">Sélectionnez une catégorie</option>
            <option value="ELECTRONICS">Électronique</option>
            <option value="CLOTHING">Vêtements</option>
            <option value="BOOKS">Livres</option>
            <option value="HOME">Maison</option>
            <option value="SPORTS">Sports</option>
            <option value="OTHER">Autre</option>
          </select>
        </div>
        <div>
          <label htmlFor="manufacturer">Fabricant:</label>
          <select
            id="manufacturer"
            name="manufacturer"
            value={formData.manufacturer}
            onChange={handleChange}
            required
          >
            <option value="">Sélectionnez un fabricant</option>
            <option value="SAMSUNG">Samsung</option>
            <option value="APPLE">Apple</option>
            <option value="SONY">Sony</option>
            <option value="DERB_GHALLEF">Derb Ghallef</option>
            <option value="NIKE">Nike</option>
            <option value="ADIDAS">Adidas</option>
            <option value="GARAGE_ALLAL">Garage Allal</option>
            <option value="OTHER">Autre</option>
          </select>
        </div>
        <div>
          <label htmlFor="isAvailable">Disponible:</label>
          <input
            type="checkbox"
            id="isAvailable"
            name="isAvailable"
            checked={formData.isAvailable}
            onChange={handleChange}
          />
        </div>
        <button type="submit">Ajouter le produit</button>
      </form>
    </div>
  )
}

export default AddProductForm


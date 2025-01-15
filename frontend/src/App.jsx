import React from 'react'
import { Link, Route, Routes } from 'react-router-dom'
import AddProductForm from './components/AddProductForm'
import ProductDetail from './components/ProductDetail'
import ProductList from './components/ProductList'
import ProductSearch from './components/ProductSearch'

function App() {
  return (
    <div className="App">
      <nav>
        <ul>
          <li><Link to="/">Liste des produits</Link></li>
          <li><Link to="/search">Rechercher un produit</Link></li>
          <li><Link to="/add">Ajouter un produit</Link></li>
        </ul>
      </nav>

      <Routes>
        <Route path="/" element={<ProductList />} />
        <Route path="/search" element={<ProductSearch />} />
        <Route path="/product/:skuCode" element={<ProductDetail />} />
        <Route path="/add" element={<AddProductForm />} />
      </Routes>
    </div>
  )
}

export default App


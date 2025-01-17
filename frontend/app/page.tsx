import ProductList from '@/components/ProductList'

export default function Home() {
  return (
    <div className="container mx-auto py-10">
      <h1 className="text-4xl font-bold mb-8">Catalogue de Produits</h1>
      <ProductList />
    </div>
  )
}


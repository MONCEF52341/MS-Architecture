import OrderList from '@/components/OrderList'
import ProductList from '@/components/ProductList'
import StudentList from '@/components/StudentList'
import { Tabs, TabsContent, TabsList, TabsTrigger } from "@/components/ui/tabs"

export default function Home() {
  return (
    <div className="container mx-auto py-10">
      <h1 className="text-4xl font-bold mb-8">Gestion du Catalogue</h1>
      
      <Tabs defaultValue="products" className="w-full">
        <TabsList className="grid w-full grid-cols-3 mb-8">
          <TabsTrigger value="products">Produits</TabsTrigger>
          <TabsTrigger value="orders">Commandes</TabsTrigger>
          <TabsTrigger value="students">Étudiants</TabsTrigger>
        </TabsList>
        
        <TabsContent value="products">
          <ProductList />
        </TabsContent>
        
        <TabsContent value="orders">
          <OrderList />
        </TabsContent>
        
        <TabsContent value="students">
          <StudentList />
        </TabsContent>
      </Tabs>
    </div>
  )
}


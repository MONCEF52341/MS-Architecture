import { Tabs, TabsContent, TabsList, TabsTrigger } from "@/components/ui/tabs"
import ProductList from "@/components/ProductList"
import OrderList from "@/components/OrderList"
import StudentList from "@/components/StudentList"
import { AddOrderForm } from "@/components/AddOrderForm"

export default function Home() {
  return (
    <div className="container mx-auto py-10">
      <h1 className="text-4xl font-bold mb-8">Gestion du Catalogue</h1>

      <Tabs defaultValue="products" className="w-full">
        <TabsList className="grid w-full grid-cols-4 mb-8">
          <TabsTrigger value="products">Produits</TabsTrigger>
          <TabsTrigger value="orders">Commandes</TabsTrigger>
          <TabsTrigger value="students">Étudiants</TabsTrigger>
          <TabsTrigger value="add-order">Ajouter une commande</TabsTrigger>
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

        <TabsContent value="add-order">
          <AddOrderForm />
        </TabsContent>
      </Tabs>
    </div>
  )
}


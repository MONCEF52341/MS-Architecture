"use client"

import { Button } from "@/components/ui/button"
import { Form, FormControl, FormField, FormItem, FormLabel, FormMessage } from "@/components/ui/form"
import { Input } from "@/components/ui/input"
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select"
import { toast } from "@/hooks/use-toast"
import { zodResolver } from "@hookform/resolvers/zod"
import { useEffect, useState } from "react"
import { useFieldArray, useForm } from "react-hook-form"
import * as z from "zod"

interface Product {
  skuCode: string
  name: string
}

const orderLineSchema = z.object({
  productName: z.string().min(1, "Veuillez sélectionner un produit"),
  quantity: z.number().min(1, "La quantité doit être d'au moins 1"),
})

const formSchema = z.object({
  customerName: z.string().min(2, "Le nom du client doit contenir au moins 2 caractères"),
  customerEmail: z.string().email("Veuillez entrer une adresse email valide"),
  shippingAddress: z.string().min(5, "L'adresse doit contenir au moins 5 caractères"),
  orderLines: z.array(orderLineSchema).min(1, "Veuillez ajouter au moins un produit à la commande"),
})

export function AddOrderForm() {
  const [products, setProducts] = useState<Product[]>([])
  const [previewBody, setPreviewBody] = useState<string>("")

  const form = useForm<z.infer<typeof formSchema>>({
    resolver: zodResolver(formSchema),
    defaultValues: {
      customerName: "",
      customerEmail: "",
      shippingAddress: "",
      orderLines: [{ productName: "", quantity: 1 }],
    },
  })

  const { fields, append, remove } = useFieldArray({
    control: form.control,
    name: "orderLines",
  })

  useEffect(() => {
    const fetchProducts = async () => {
      try {
        const response = await fetch("http://localhost:9000/api/products")
        const data = await response.json()
        setProducts(data)
      } catch (error) {
        console.error("Erreur lors de la récupération des produits:", error)
        toast({
          title: "Erreur",
          description: "Impossible de charger la liste des produits.",
          variant: "destructive",
        })
      }
    }

    fetchProducts()
  }, [])

  const generatePreviewBody = (data: z.infer<typeof formSchema>) => {
    const orderLines = data.orderLines.map((line) => ({
      skuCode: products.find((p) => p.name === line.productName)?.skuCode,
      quantity: line.quantity,
    }))

    const previewData = {
      orderNumber: `ORD-${Math.random().toString(36).substr(2, 6).toUpperCase()}`,
      orderDate: new Date().toISOString(),
      customerName: data.customerName,
      customerEmail: data.customerEmail,
      shippingAddress: data.shippingAddress,
      status: "PENDING",
      orderLines,
    }

    setPreviewBody(JSON.stringify(previewData, null, 2))
  }

  const onSubmit = async (data: z.infer<typeof formSchema>) => {
    try {
      const orderLines = data.orderLines.map((line) => ({
        skuCode: products.find((p) => p.name === line.productName)?.skuCode,
        quantity: line.quantity,
      }))

      const orderData = {
        customerName: data.customerName,
        customerEmail: data.customerEmail,
        shippingAddress: data.shippingAddress,
        orderLines,
      }

      const response = await fetch("http://localhost:9000/api/orders", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(orderData),
      })

      if (!response.ok) {
        throw new Error("Erreur lors de la création de la commande")
      }

      toast({
        title: "Commande créée",
        description: "La commande a été créée avec succès.",
      })
      form.reset()
      setPreviewBody("")
    } catch (error) {
      console.error("Erreur:", error)
      toast({
        title: "Erreur",
        description: "Une erreur est survenue lors de la création de la commande.",
        variant: "destructive",
      })
    }
  }

  return (
    <div className="space-y-8">
      <Form {...form}>
        <form onSubmit={form.handleSubmit(onSubmit)} className="space-y-8">
          <FormField
            control={form.control}
            name="customerName"
            render={({ field }) => (
              <FormItem>
                <FormLabel>Nom du client</FormLabel>
                <FormControl>
                  <Input {...field} />
                </FormControl>
                <FormMessage />
              </FormItem>
            )}
          />
          <FormField
            control={form.control}
            name="customerEmail"
            render={({ field }) => (
              <FormItem>
                <FormLabel>Email du client</FormLabel>
                <FormControl>
                  <Input {...field} type="email" />
                </FormControl>
                <FormMessage />
              </FormItem>
            )}
          />
          <FormField
            control={form.control}
            name="shippingAddress"
            render={({ field }) => (
              <FormItem>
                <FormLabel>Adresse de livraison</FormLabel>
                <FormControl>
                  <Input {...field} />
                </FormControl>
                <FormMessage />
              </FormItem>
            )}
          />
          {fields.map((field, index) => (
            <div key={field.id} className="flex items-end space-x-4">
              <FormField
                control={form.control}
                name={`orderLines.${index}.productName`}
                render={({ field }) => (
                  <FormItem className="flex-grow">
                    <FormLabel>Produit</FormLabel>
                    <Select onValueChange={field.onChange} defaultValue={field.value}>
                      <FormControl>
                        <SelectTrigger>
                          <SelectValue placeholder="Sélectionnez un produit" />
                        </SelectTrigger>
                      </FormControl>
                      <SelectContent>
                        {products.map((product) => (
                          <SelectItem key={product.skuCode} value={product.name}>
                            {product.name}
                          </SelectItem>
                        ))}
                      </SelectContent>
                    </Select>
                    <FormMessage />
                  </FormItem>
                )}
              />
              <FormField
                control={form.control}
                name={`orderLines.${index}.quantity`}
                render={({ field }) => (
                  <FormItem>
                    <FormLabel>Quantité</FormLabel>
                    <FormControl>
                      <Input
                        type="number"
                        {...field}
                        onChange={(e) => field.onChange(Number.parseInt(e.target.value, 10))}
                      />
                    </FormControl>
                    <FormMessage />
                  </FormItem>
                )}
              />
              <Button type="button" variant="outline" onClick={() => remove(index)}>
                Supprimer
              </Button>
            </div>
          ))}
          <Button type="button" variant="outline" onClick={() => append({ productName: "", quantity: 1 })}>
            Ajouter un produit
          </Button>
          <div className="flex space-x-4">
            <Button type="submit">Créer la commande</Button>
            <Button type="button" variant="secondary" onClick={() => generatePreviewBody(form.getValues())}>
              Générer l'aperçu
            </Button>
          </div>
        </form>
      </Form>
      {previewBody && (
        <div className="mt-8">
          <h3 className="text-lg font-semibold mb-2">Aperçu du body de la requête :</h3>
          <pre className="bg-gray-100 p-4 rounded-md overflow-x-auto">{previewBody}</pre>
        </div>
      )}
    </div>
  )
}


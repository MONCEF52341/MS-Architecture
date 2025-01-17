import { Badge } from '@/components/ui/badge'
import { Card, CardContent, CardFooter, CardHeader, CardTitle } from '@/components/ui/card'
import { Product } from '@/types/product'
import Image from 'next/image'

interface ProductCardProps {
  product: Product
}

const randomImageId = Math.floor(Math.random() * 300) + 1;



export default function ProductCard({ product }: ProductCardProps) {
  const imageUrl = `https://picsum.photos/id/${((randomImageId*Math.floor(Math.random() * 300) + 1)/1000).toFixed(0)}/300/100`

  return (
    <Card className="overflow-hidden">
      <CardHeader className="p-0">
        <div className="relative h-48 w-full">
          <Image
            src={imageUrl || "/placeholder.svg"}
            alt={product.name}
            layout="fill"
            objectFit="cover"
          />
        </div>
      </CardHeader>
      <CardContent className="p-4">
        <CardTitle className="text-lg font-semibold mb-2">{product.name}</CardTitle>
        <p className="text-sm text-gray-600 mb-2">{product.description}</p>
        <div className="flex justify-between items-center">
          <span className="text-lg font-bold">{product.price.toFixed(2)} €</span>
          <Badge variant={product.isAvailable ? "default" : "secondary"}>
            {product.isAvailable ? "Disponible" : "Indisponible"}
          </Badge>
        </div>
      </CardContent>
      <CardFooter className="p-4 pt-0 flex justify-between text-sm text-gray-500">
        <span>Stock: {product.stockQuantity}</span>
        <span>{product.category}</span>
      </CardFooter>
    </Card>
  )
}


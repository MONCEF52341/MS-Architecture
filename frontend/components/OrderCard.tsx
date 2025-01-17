import { format } from 'date-fns'
import { fr } from 'date-fns/locale'
import { Card, CardContent, CardHeader } from '@/components/ui/card'
import { Badge } from '@/components/ui/badge'
import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "@/components/ui/table"
import { Order } from '@/types/order'
import { ChevronDown } from 'lucide-react'
import { Accordion, AccordionContent, AccordionItem, AccordionTrigger } from "@/components/ui/accordion"

interface OrderCardProps {
  order: Order
}

export default function OrderCard({ order }: OrderCardProps) {
  const getStatusColor = (status: string) => {
    const colors = {
      'PENDING': 'bg-yellow-500 hover:bg-yellow-600',
      'CONFIRMED': 'bg-blue-500 hover:bg-blue-600',
      'SHIPPED': 'bg-purple-500 hover:bg-purple-600',
      'DELIVERED': 'bg-green-500 hover:bg-green-600',
      'REJECTED': 'bg-red-500 hover:bg-red-600'
    }
    return colors[status as keyof typeof colors] || 'bg-gray-500 hover:bg-gray-600'
  }

  const formatDate = (dateString: string) => {
    return format(new Date(dateString), 'dd MMMM yyyy à HH:mm', { locale: fr })
  }

  return (
    <Card className="hover:shadow-lg transition-all duration-300 ease-in-out">
      <CardHeader className="flex flex-row items-center justify-between space-y-0 pb-2">
        <div>
          <h3 className="text-lg font-semibold">{order.orderNumber}</h3>
          <p className="text-xs text-muted-foreground">
            {formatDate(order.orderDate)}
          </p>
        </div>
        <Badge className={`${getStatusColor(order.status)} transition-colors duration-300`}>
          {order.status}
        </Badge>
      </CardHeader>
      <CardContent>
        <Accordion type="single" collapsible className="w-full">
          <AccordionItem value="details">
            <AccordionTrigger className="text-sm">
              Détails de la commande
            </AccordionTrigger>
            <AccordionContent>
              <div className="space-y-2 text-sm">
                <div><span className="font-semibold">Client :</span> {order.customerName}</div>
                <div><span className="font-semibold">Email :</span> {order.customerEmail}</div>
                <div><span className="font-semibold">Adresse :</span> {order.shippingAddress}</div>
                <Table>
                  <TableHeader>
                    <TableRow>
                      <TableHead className="w-[100px]">SKU</TableHead>
                      <TableHead className="text-right">Qté</TableHead>
                    </TableRow>
                  </TableHeader>
                  <TableBody>
                    {order.orderLines.map((line) => (
                      <TableRow key={line.skuCode}>
                        <TableCell className="font-medium">{line.skuCode}</TableCell>
                        <TableCell className="text-right">{line.quantity}</TableCell>
                      </TableRow>
                    ))}
                  </TableBody>
                </Table>
              </div>
            </AccordionContent>
          </AccordionItem>
        </Accordion>
      </CardContent>
    </Card>
  )
}


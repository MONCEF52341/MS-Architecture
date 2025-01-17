"use client"

import { Badge } from '@/components/ui/badge'
import { Button } from "@/components/ui/button"
import { Checkbox } from "@/components/ui/checkbox"
import { DataTable } from '@/components/ui/data-table'
import {
  DropdownMenu,
  DropdownMenuContent,
  DropdownMenuItem,
  DropdownMenuLabel,
  DropdownMenuSeparator,
  DropdownMenuTrigger,
} from "@/components/ui/dropdown-menu"
import { Student } from '@/types/student'
import { ColumnDef } from "@tanstack/react-table"
import { format } from 'date-fns'
import { fr } from 'date-fns/locale'
import { ArrowUpDown, MoreHorizontal } from 'lucide-react'
import { useEffect, useState } from 'react'

export default function StudentList() {
  const [students, setStudents] = useState<Student[]>([])

  useEffect(() => {
    async function fetchStudents() {
      try {
        const response = await fetch('http://localhost:9000/api/students')
        const data = await response.json()
        setStudents(data)
      } catch (error) {
        console.error('Erreur lors de la récupération des étudiants:', error)
      }
    }

    fetchStudents()
  }, [])

  const columns: ColumnDef<Student>[] = [
    {
      id: "select",
      header: ({ table }) => (
        <Checkbox
          checked={table.getIsAllPageRowsSelected()}
          onCheckedChange={(value) => table.toggleAllPageRowsSelected(!!value)}
          aria-label="Select all"
        />
      ),
      cell: ({ row }) => (
        <Checkbox
          checked={row.getIsSelected()}
          onCheckedChange={(value) => row.toggleSelected(!!value)}
          aria-label="Select row"
        />
      ),
      enableSorting: false,
      enableHiding: false,
    },
    {
      accessorKey: "name",
      header: ({ column }) => {
        return (
          <Button
            variant="ghost"
            onClick={() => column.toggleSorting(column.getIsSorted() === "asc")}
          >
            Nom
            <ArrowUpDown className="ml-2 h-4 w-4" />
          </Button>
        )
      },
      cell: ({ row }) => (
        <div>
          {row.original.firstName} {row.original.lastName}
        </div>
      ),
    },
    {
      accessorKey: "serial",
      header: "Numéro d'étudiant",
    },
    {
      accessorKey: "dateOfBirth",
      header: "Date de naissance",
      cell: ({ row }) => format(new Date(row.original.dateOfBirth), 'dd MMMM yyyy', { locale: fr }),
    },
    {
      accessorKey: "email",
      header: "Email",
    },
    {
      accessorKey: "major",
      header: "Filière",
      cell: ({ row }) => {
        const majorColors: { [key: string]: string } = {
          'ENGINEERING': 'bg-blue-500',
          'CHEMISTRY': 'bg-green-500',
          'PHYSICS': 'bg-purple-500',
          'MATHEMATICS': 'bg-red-500',
          'BIOLOGY': 'bg-yellow-500'
        }
        return (
          <Badge className={`${majorColors[row.original.major] || 'bg-gray-500'}`}>
            {row.original.major}
          </Badge>
        )
      },
    },
    {
      accessorKey: "yearOfStudy",
      header: "Année d'étude",
    },
    {
      id: "actions",
      cell: ({ row }) => {
        const student = row.original
 
        return (
          <DropdownMenu>
            <DropdownMenuTrigger asChild>
              <Button variant="ghost" className="h-8 w-8 p-0">
                <span className="sr-only">Ouvrir le menu</span>
                <MoreHorizontal className="h-4 w-4" />
              </Button>
            </DropdownMenuTrigger>
            <DropdownMenuContent align="end">
              <DropdownMenuLabel>Actions</DropdownMenuLabel>
              <DropdownMenuItem
                onClick={() => navigator.clipboard.writeText(student.email)}
              >
                Copier l'email
              </DropdownMenuItem>
              <DropdownMenuSeparator />
              <DropdownMenuItem>Voir les détails</DropdownMenuItem>
              <DropdownMenuItem>Modifier l'étudiant</DropdownMenuItem>
            </DropdownMenuContent>
          </DropdownMenu>
        )
      },
    },
  ]

  return (
    <div className="container mx-auto py-10">
      <DataTable columns={columns} data={students} />
    </div>
  )
}


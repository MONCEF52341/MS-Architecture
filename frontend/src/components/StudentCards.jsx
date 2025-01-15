import React, { useState, useEffect } from 'react'

function StudentCards() {
  const [students, setStudents] = useState([])
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState(null)

  useEffect(() => {
    fetch('http://localhost:9000/api/students')
      .then(response => {
        if (!response.ok) {
          throw new Error('Failed to fetch students')
        }
        return response.json()
      })
      .then(data => {
        setStudents(data)
        setLoading(false)
      })
      .catch(error => {
        setError(error.message)
        setLoading(false)
      })
  }, [])

  if (loading) return <div>Loading students...</div>
  if (error) return <div>Error: {error}</div>

  return (
    <div className="student-cards">
      <h2>Student Cards</h2>
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
        {students.map(student => (
          <div key={student.serial} className="bg-white shadow-md rounded-lg p-4">
            <h3 className="text-lg font-semibold">{`${student.firstName} ${student.lastName}`}</h3>
            <p>Serial: {student.serial}</p>
            <p>Email: {student.email}</p>
            <p>Major: {student.major}</p>
            <p>Year of Study: {student.yearOfStudy}</p>
            <p>Date of Birth: {student.dateOfBirth}</p>
            <p>Phone: {student.phoneNumber}</p>
            <p>Address: {student.address}</p>
          </div>
        ))}
      </div>
    </div>
  )
}

export default StudentCards


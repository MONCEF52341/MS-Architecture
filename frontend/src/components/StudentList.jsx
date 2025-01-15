import React, { useState, useEffect } from 'react'

function StudentList() {
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
    <div className="student-list">
      <h2>Student List</h2>
      <table className="w-full">
        <thead>
          <tr>
            <th>Serial</th>
            <th>Name</th>
            <th>Email</th>
            <th>Major</th>
            <th>Year</th>
          </tr>
        </thead>
        <tbody>
          {students.map(student => (
            <tr key={student.serial}>
              <td>{student.serial}</td>
              <td>{`${student.firstName} ${student.lastName}`}</td>
              <td>{student.email}</td>
              <td>{student.major}</td>
              <td>{student.yearOfStudy}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  )
}

export default StudentList


import React, { useEffect, useState } from "react";
import axios from 'axios';
import { NumericFormat } from 'react-number-format'
import { Link } from "react-router-dom";
export default function () {

    const urlBase="http://localhost:8080/rh-app/empleados"
      const [empleados,setEmpleados]=useState([]);

      useEffect(()=>{
        cargarEmpleados();
      },[])

      const cargarEmpleados= async()=>{
       const resultados= await axios.get(urlBase);
        console.log("resultados de cargar empleados")
        console.log(resultados)
        setEmpleados(resultados.data)
      }
       const eliminarEmpleado=async(id)=>{
        await axios.delete(`${urlBase}/${id}`);
        cargarEmpleados();
       }

  return (
    <div className="container ">
      <div className="container text-center " style={{ margin: "30px" }}>
        <h3>Sistema de Recursos Humanos</h3>
      </div>
      <table className="table table-striped table-hover align-middle">
        <thead className="table-dark">
          <tr>
            <th scope="col">id</th>
            <th scope="col">Empleado</th>
            <th scope="col">Departamento</th>
            <th scope="col">sueldo</th>
            <th scope="col"></th>
          </tr>
        </thead>
        <tbody>
            {
              empleados.map((empleado,indice)=>(
                <tr key={indice}>
                <th scope="row">{empleado.idEmpleado} </th>
                <td>{empleado.nombre} </td>
                <td>{empleado.departamento} </td>
                <td><NumericFormat value={empleado.sueldo} displayType={"text"} thousandSeparator="," prefix={"$"}
                decimalScale={2} fixeDecimalScale/>
             </td>
             <td className="tex-center">
                <div>
                  <Link to={`/editar/${empleado.idEmpleado}`} className="btn btn-warning btn-sm me-3" >Editar</Link>
                </div>
                <div>
                  <button  className="btn btn-danger btn-sm"  onClick={()=>eliminarEmpleado(empleado.idEmpleado)}>Elimnar </button>
                </div>
              </td>
              </tr>
              
            ))
            }
        
        </tbody>
      </table>
    </div>
  );
}

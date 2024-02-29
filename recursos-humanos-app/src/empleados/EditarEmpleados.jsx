import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import axios from "axios";
export default function EditarEmpleado() {
  const navigate = useNavigate();

  const urlBase = "http://localhost:8080/rh-app/empleados";
  const { id } = useParams();
  const [empleado, setEmpleado] = useState({
    nombre: "",
    departamento: "",
    sueldo: "",
  });
  const { nombre, departamento, sueldo } = empleado;
  useEffect(() => {
    cargarEmpleado();
  },[]);

  const cargarEmpleado = async () => {
    const resultado = await axios.get(`${urlBase}/${id}`);
    console.log(resultado.data)
    setEmpleado(resultado.data);
  };
  const onInputChange = ({ target }) => {
    const { value, name } = target;
    setEmpleado((prevState) => ({
      ...prevState,
      [name]: value,
    }));
  };
  const onsubmit = async (e) => {
    e.preventDefault();
    await axios.put(`${urlBase}/${id}`, empleado);
    navigate("/");
  };
  return (
    <div className="container">
      <div className="container text-center" style={{ margin: "30px" }}>
        <h3>Editar Empleado</h3>
      </div>
      <form onSubmit={onsubmit}>
        <div className="mb-3">
          <label htmlFor="nombre" className="form-label">
            nombre
          </label>
          <input
            type="text"
            className="form-control"
            id="nombre"
            name="nombre"
            value={nombre}
            onChange={(e) => onInputChange(e)}
            required={true}
          />
        </div>
        <div className="mb-3">
          <label htmlFor="departamento" className="form-label">
            departamento
          </label>
          <input
            type="text"
            className="form-control"
            id="departamento"
            name="departamento"
            value={departamento}
            onChange={(e) => onInputChange(e)}
          />
        </div>
        <div className="mb-3">
          <label htmlFor="sueldo" className="form-label">
            sueldo
          </label>
          <input
            type="number"
            step="any"
            className="form-control"
            id="sueldo"
            name="sueldo"
            value={sueldo}
            onChange={(e) => onInputChange(e)}
          />
        </div>
        <div className="text-center">
          <button type="submit" className="btn btn-warning btn-sm me-3">
            Guardar
          </button>
          <a href="/" className="btn btn-danger btn-sm ">
            Regresar
          </a>
        </div>
      </form>
    </div>
  );
}

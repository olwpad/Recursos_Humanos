import { useState } from "react";
import ListadoEmpleados from "./empleados/ListadoEmpleados";
import Navegacion from "./plantilla/Navegacion";
import { BrowserRouter as Router, Routes,Route } from "react-router-dom";
import AgregarEmpleado from "./empleados/AgregarEmpleado";
import EditarEmpleado from "./empleados/EditarEmpleados";
function App() {
  const [count, setCount] = useState(0);

  return (
    <div className="container">
      <Router>
      <Navegacion />
        <Routes>
          <Route exact path="/" element={<ListadoEmpleados/>}  />
          <Route exact path="/agregar" element={<AgregarEmpleado/>}  />
          <Route exact path="/editar/:id" element={<EditarEmpleado/>}  />
        </Routes>
      </Router>
    </div>
  );
}

export default App;

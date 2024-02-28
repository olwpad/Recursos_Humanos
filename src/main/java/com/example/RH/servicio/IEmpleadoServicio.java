package com.example.RH.servicio;

import com.example.RH.modelo.Empleado;

import java.util.List;

public interface IEmpleadoServicio {
    public List<Empleado> listarEmpleados();
    public Empleado guardarEmpleado(Empleado empleado);

    public void elimnarEmpleado(Empleado empleado);
    public Empleado buscarEmpleadoPorId(Integer idEmpleado);

}

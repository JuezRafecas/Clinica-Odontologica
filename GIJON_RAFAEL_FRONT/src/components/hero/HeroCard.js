import { useState } from 'react'
import axios from 'axios';
import { useForm } from '../../hooks/useForm';
import { types } from '../../types/types';


export const HeroCard = ({
    id,
    nombre,
    apellido,
    matricula,
    fechaIngreso,
    dni,
    typePeople
}) => {

    const [openForm, setopenForm] = useState(false)
    const handleModify = (e) => {
        e.preventDefault();
        setopenForm(true);
    }

    const [error, seterror] = useState(false);

    const [isDelete, setisDelete] = useState(false)

    const [formValues, handleInputChange] = useForm({
        newNombre: nombre,
        newApellido: apellido,
        newMatricula: matricula,
        newFechaIngreso: fechaIngreso,
        newDni: dni,
    });

    const { newNombre,
        newApellido,
        newMatricula,
        newFechaIngreso,
        newDni } = formValues;


    const handleSubmit = (e) => {
        e.preventDefault();
        let data = {
            nombre: newNombre,
            apellido: newApellido,
            id,
        }
        switch (typePeople) {
            case 'odontologos':
                data = {
                    ...data,
                    matricula: newMatricula,
                }
                break;

            case 'pacientes':
                data = {
                    ...data,
                    dni: newDni,
                }
                break;
            default:
                break;
        }
        data = JSON.stringify(data);
        const config = {
            method: 'PUT',
            url: `http://localhost:8080/${typePeople}`,
            headers: {
                'Content-Type': 'application/json',
                Authorization: `Bearer ${JSON.parse(localStorage.getItem("user")).jwt}`
            },
            data,
        };
        axios(config)
            .then(function (response) {
                setopenForm(false);
            })
            .catch(function (error) {
                seterror(true);
            });
    }

    const handleDelete = () =>{
        const config = {
            method: 'DELETE',
            url: `http://localhost:8080/${typePeople}/${id}`,
            headers: {
                'Content-Type': 'application/json',
                Authorization: `Bearer ${JSON.parse(localStorage.getItem("user")).jwt}`
            },
        };
        axios(config)
            .then(function (response) {
                window.location.reload();
            })
            .catch(function (error) {
                seterror(true);
            });
    }


    return (
        <div className="col animate__animated animate__fadeIn">
            {!isDelete &&
            <div className="card">

                <div className="row no-gutters">
                    <div className="col-4">
                        <img
                            src={matricula
                                ? 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQV-EupTjQYXzp4yLUc3mefU8TmlUhLMOk15Q&usqp=CAU'
                                : 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRBwr_zZjgvmu4BccwDNIHic8K5dyehw7cSYA&usqp=CAU'
                            }
                            className="card-img card-img-equal"
                            alt={newNombre} />
                    </div>
                    <div className="col-8">

                        <div className="card-body">

                            <h5 className="card-title">{newNombre + ' ' + newApellido}</h5>
                            {matricula && <p className="card-text">Matricula: {newMatricula}</p>}
                            {dni && <p className="card-text">DNI: {newDni}</p>}

                            <p className="card-text">
                                <small className="text-muted">{fechaIngreso}</small>
                            </p>

                            <div style={{ display: 'flex', gap: '10px' }}>
                                <button
                                    type="button"
                                    className="btn btn-primary"
                                    onClick={handleModify}
                                >
                                    Modificar
                                </button>

                                <button 
                                    type="button" 
                                    className="btn btn-danger"
                                    onClick={handleDelete}
                                >
                                    Eliminar
                                </button>
                            </div>

                        </div>

                        {openForm &&
                            <div>
                                <form onSubmit={handleSubmit} className='m-2'>
                                    <input
                                        type="text"
                                        placeholder="Nombre..."
                                        className="form-control"
                                        name="newNombre"
                                        autoComplete="off"
                                        value={newNombre}
                                        onChange={handleInputChange}
                                    />
                                    <input
                                        type="text"
                                        placeholder="Apellido..."
                                        className="form-control mt-1"
                                        name="newApellido"
                                        autoComplete="off"
                                        value={newApellido}
                                        onChange={handleInputChange}
                                    />
                                    {matricula && <input
                                        type="number"
                                        placeholder="Matricula..."
                                        className="form-control mt-1"
                                        name="newMatricula"
                                        autoComplete="off"
                                        value={newMatricula}
                                        onChange={handleInputChange}
                                    />}
                                    {dni && <input
                                        type="number"
                                        placeholder="DNI..."
                                        className="form-control mt-1"
                                        name="newDni"
                                        autoComplete="off"
                                        value={newDni}
                                        onChange={handleInputChange}
                                    />}
                                    {error && <div className='invalid-feedback' style={{ display: 'flex' }}>Revisar los campos</div>}
                                    <button
                                        className="btn btn-outline-primary mt-1"
                                        type="submit">
                                        Guardar cambios
                                    </button>

                                </form>
                            </div>}
                    </div>
                </div>

            </div>
            }
        </div>
    )
}

import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import axios from 'axios';
import { useEffect, useState } from 'react';
import { Autocomplete, Box, Button, Modal, TextField, Typography } from '@mui/material';

const style = {
    position: 'absolute',
    top: '50%',
    left: '50%',
    transform: 'translate(-50%, -50%)',
    width: 400,
    bgcolor: 'background.paper',
    border: '2px solid #000',
    boxShadow: 24,
    p: 4,
    background: 'white !important'
};


export default function Turnos() {

    let [turnos, setTurnos] = useState([]);
    const [pacientes, setPacientes] = useState([]);
    const [odontologos, setOdontologos] = useState([]);
    const [open, setOpen] = useState(false);
    const handleOpen = () => setOpen(true);
    const handleClose = () => setOpen(false);
    useEffect(() => {
        axios.get(`http://localhost:8080/turnos`, {
            headers: {
                Authorization: `Bearer ${JSON.parse(localStorage.getItem("user")).jwt}`
            }
        }).then((response) => {
            setTurnos(response.data);
        }).catch((error) => {
            console.log(error);
        });
    }, []);

    useEffect(() => {
        axios.get(`http://localhost:8080/odontologos`, {
            headers: {
                Authorization: `Bearer ${JSON.parse(localStorage.getItem("user")).jwt}`
            }
        }).then((response) => {
            const odontologos = response.data.map(odontologo => {
                return {
                    ...odontologo,
                    label: odontologo.nombre + ' ' + odontologo.apellido
                }
            })
            setOdontologos(odontologos);
        }).catch((error) => {
            console.log(error);
        });
    }, []);

    useEffect(() => {
        axios.get(`http://localhost:8080/pacientes`, {
            headers: {
                Authorization: `Bearer ${JSON.parse(localStorage.getItem("user")).jwt}`
            }
        }).then((response) => {
            const pacientes = response.data.map(pacient => {
                return {
                    ...pacient,
                    label: pacient.nombre + ' ' + pacient.apellido
                }
            })
            setPacientes(pacientes);
        }).catch((error) => {
            console.log(error);
        });
    }, []);

    const handleAdd = () => {
        const formHTML = document.querySelector('form.add-form');
        const inputsHTML = formHTML.querySelectorAll('input');
        let nombrePaciente = inputsHTML[0].value;
        let nombreOdontologo = inputsHTML[1].value;
        let fecha = inputsHTML[2].value;
        let data = {
            paciente: pacientes.find(paciente => (paciente.nombre + ' ' + paciente.apellido) === nombrePaciente),
            odontologo: odontologos.find(odontologo => (odontologo.nombre + ' ' + odontologo.apellido) === nombreOdontologo),
            fecha,
        };
        data = JSON.stringify(data);
        const config = {
            method: 'POST',
            url: `http://localhost:8080/turnos`,
            headers: {
                'Content-Type': 'application/json',
                Authorization: `Bearer ${JSON.parse(localStorage.getItem("user")).jwt}`
            },
            data,
        };
        axios(config)
        .then(function (response) {
            window.location.reload();
        })
        setOpen(false);
        // window.location.reload();
    }

    const handleDelete = (e, id) =>{
        const config = {
            method: 'DELETE',
            url: `http://localhost:8080/turnos/${id}`,
            headers: {
                'Content-Type': 'application/json',
                Authorization: `Bearer ${JSON.parse(localStorage.getItem("user")).jwt}`
            },
        };
        axios(config)
        .then(function (response) {
            window.location.reload();
        })
    }


    const options = { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric', hour: 'numeric', minute: 'numeric'};

    turnos = turnos.sort((a, b) => {
        return new Date(a.fecha) - new Date(b.fecha);
    })

    console.log(turnos);

    return (
        <div>
            <h1>Turnos</h1>
            <hr />
            <TableContainer component={Paper}>
                <Table aria-label="simple table">
                    <TableHead>
                        <TableRow>
                            <TableCell>Paciente</TableCell>
                            <TableCell>Doctor</TableCell>
                            <TableCell>Matricula</TableCell>
                            <TableCell>Fecha</TableCell>
                            <TableCell>Eliminar</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {turnos.map((turno) => (
                            <TableRow
                                key={turno.id}
                                sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                            >
                                <TableCell component="th" scope="row">
                                    {turno.paciente.apellido}
                                </TableCell>
                                <TableCell>{turno.odontologo.apellido}</TableCell>
                                <TableCell>{turno.odontologo.matricula}</TableCell>
                                <TableCell>{turno.fecha}</TableCell>
                                <TableCell>
                                    <Button
                                        variant='contained'
                                        color='error'
                                        onClick={(e) => handleDelete(e,turno.id)}
                                    >
                                        ELIMINAR TURNO
                                    </Button>
                                </TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
                <Box sx={{ margin: '3em 1em 2em 3em' }}>
                    <Button
                        onClick={handleOpen}
                        variant='contained'
                        className="animate__animated animate__fadeIn">
                        AGREGAR TURNO
                    </Button>
                </Box>
            </TableContainer>
            <Modal
                open={open}
                onClose={handleClose}
                aria-labelledby="modal-modal-title"
                aria-describedby="modal-modal-description"
            >
                <Box sx={style}>
                    <Typography id="modal-modal-title" variant="h6" component="h2">
                        Agregando un nuevo turno
                    </Typography>
                    <form className='add-form'>
                        <Box sx={{
                            display: 'flex',
                            justifyContent: 'center',
                            alignItems: 'center',
                            gap: '10px',
                            flexDirection: 'column',
                            padding: '1em'
                        }}>
                            <Autocomplete
                                disablePortal
                                id="combo-box-demo"
                                options={pacientes}
                                fullWidth
                                renderInput={(params) => <TextField {...params} label="Paciente" />}
                            />
                            <Autocomplete
                                disablePortal
                                id="combo-box-demo"
                                options={odontologos}
                                fullWidth
                                renderInput={(params) => <TextField {...params} label="Odontologo" />}
                            />
                            <TextField
                                id="datetime-local"
                                label='Horario'
                                type="datetime-local"
                                defaultValue="2022-04-24T20:30"
                                fullWidth
                                InputLabelProps={{
                                    shrink: true,
                                }}
                            />
                            <Button
                                variant='contained'
                                onClick={handleAdd}
                            >
                                AGREGAR TURNO
                            </Button>
                        </Box>
                    </form>
                </Box>
            </Modal>
        </div>
    );
}
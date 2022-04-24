import { useMemo } from 'react';

import { HeroCard } from './HeroCard';
import { useEffect, useState } from 'react';
import axios from 'axios';
import { Box } from '@mui/system';
import { Button, Modal, TextField, Typography } from '@mui/material';


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

export const HeroList = ({ typePeople = 'odontologos' }) => {

    // const heroes = useMemo( () => getHeroesByPublisher( publisher ), [ publisher ] );

    const [people, setpeople] = useState([])
    const [open, setOpen] = useState(false);
    const handleOpen = () => setOpen(true);
    const handleClose = () => setOpen(false);

    useEffect(() => {
        axios.get(`http://localhost:8080/${typePeople}`, {
            headers: {
                Authorization: `Bearer ${JSON.parse(localStorage.getItem("user")).jwt}`
            }
        }).then((response) => {
            setpeople(response.data);
        }).catch((error) => {
            console.log(error);
        });
    }, []);

    const handleAdd = () =>{
        const formHTML = document.querySelector('form.add-form');
        const inputsHTML = formHTML.querySelectorAll('input');
        let data = {
            nombre: inputsHTML[0].value,
            apellido: inputsHTML[1].value,
        };
        if(typePeople === 'odontologos'){
            data = {
                ...data,
                matricula: inputsHTML[2].value,
            }
        } else{
            data = {
                ...data,
                dni: inputsHTML[2].value,
            }
        }
        data = JSON.stringify(data);
        const config = {
            method: 'POST',
            url: `http://localhost:8080/${typePeople}`,
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
    }

    return (
        <Box display='flex' flexWrap className="animate__animated animate__fadeIn">
                    <Box display='flex' flexWrap sx={{ gap: '2em' }}>
                        {people.map(hero => (
                            <HeroCard
                                key={hero.id}
                                {...hero}
                                typePeople={typePeople}
                            />
                        ))
                        }
                        <Box>
                            <Button
                                onClick={handleOpen}
                                variant='contained'
                                sx={{ height: '11.4em' }}
                                className="animate__animated animate__fadeIn">
                                AGREGAR {typePeople.slice(0, -1)}
                            </Button>
                        </Box>
                    </Box>
            <Modal
                open={open}
                onClose={handleClose}
                aria-labelledby="modal-modal-title"
                aria-describedby="modal-modal-description"
            >
                <Box sx={style}>
                    <Typography id="modal-modal-title" variant="h6" component="h2">
                        Agregando un nuevo {typePeople.slice(0, -1)}
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
                            <TextField id="nombre" fullWidth label="Nombre" variant="outlined" />
                            <TextField id="apellido" fullWidth label="Apellido" variant="outlined" />
                            {typePeople === 'pacientes'&& <TextField id="outlined-basic" fullWidth label="DNI" variant="outlined" />}
                            {typePeople === 'odontologos'&& <TextField id="outlined-basic" fullWidth label="Matricula" variant="outlined" />}
                            <Button
                                variant='contained'
                                onClick={handleAdd}
                            >
                                AGREGAR {typePeople.slice(0, -1)}
                            </Button>
                        </Box>
                    </form>
                </Box>
            </Modal>
        </Box>
    )
}

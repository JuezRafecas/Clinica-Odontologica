import axios from 'axios';
import { useContext } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import { useState } from 'react';
import { AuthContext } from '../../auth/authContext';
import { useForm } from '../../hooks/useForm';
import { types } from '../../types/types';


export const LoginScreen = () => {

    const location = useLocation();
    const [error, seterror] = useState(false)
    
    const [ formValues, handleInputChange ] = useForm({
        username: '',
        password: '',
    });

    const { username, password } = formValues;

    const navigate = useNavigate();
    const { dispatch } = useContext( AuthContext )


    const handleLogin = (e) => {
        e.preventDefault();
        const config = {
            method: 'post',
            url: 'http://localhost:8080/users/signin',
            headers: { 
            'Content-Type': 'application/json', 
            },
            data : formValues
        };
        axios(config)
        .then(function (response) {
            // localStorage.setItem("jwt", response.data.jwt);
            // localStorage.setItem("role", response.data.authorities[0].authority);
            // navigate("/");
            const action = {
                type: types.login,
                payload: {
                    username: username.toLowerCase(),
                    jwt: response.data.jwt,
                    role: 'ROLE_ADMIN'
                },
            }
    
            dispatch(action);
    
            const lastPath = localStorage.getItem('lastPath') || '/marvel';
    
    
            navigate( lastPath, {
                replace: true
            });
        })
        .catch(function (error) {
            seterror(true);
        });
    }

    return (
        <div className="container mt-5">
            <h1>Login Clinica Odontologica</h1>
            <hr />
            <p>Para ingresar como Admin: Usuario: henry - Password: admin</p>


            <form onSubmit={ handleLogin }>
                        <input 
                            type="text"
                            placeholder="Usuario"
                            className="form-control"
                            name="username"
                            autoComplete="off"
                            value={ username }
                            onChange={ handleInputChange }
                        />
                        <input 
                            type="password"
                            placeholder="******"
                            className="form-control mt-1"
                            name="password"
                            autoComplete="off"
                            value={ password }
                            onChange={ handleInputChange }
                        />
                        {error && <div className='invalid-feedback' style={{display: 'flex'}}>Usuario o contraseña incorrecta</div>}
                        <button 
                            className="btn btn-outline-primary mt-1"
                            type="submit">
                            Login
                        </button>

                    </form>
        </div>
    )
}

import { Routes, Route } from 'react-router-dom';

import { Navbar } from '../components/ui/Navbar';

import { Pacientes } from '../components/Pacientes/Pacientes';
import { Odontologos } from '../components/Odontologos/Odontologos';
import { HeroScreen } from '../components/hero/HeroScreen';
import Turnos from '../components/turnos/turnos';


export const DashboardRoutes = () => {
    return (
        <>
            <Navbar />

            <div className="container">
                <Routes>
                    <Route path="odontologos" element={<Odontologos />} />
                    <Route path="pacientes" element={<Pacientes />} />
                    <Route path="turnos" element={<Turnos />} />
                    <Route path="odontologos/:id" element={<HeroScreen />} />
                    <Route path="pacientes/:id" element={<HeroScreen />} />
                    <Route path="/" element={<Odontologos />} />

                </Routes>
            </div>
        </>
    )
}

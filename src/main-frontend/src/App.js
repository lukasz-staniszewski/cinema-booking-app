import {Routes, Route, Navigate} from "react-router-dom";
import Layout from "./components/layout/Layout";
import React, {Suspense} from "react";
import "react-loader-spinner/dist/loader/css/react-spinner-loader.css"
import Loader from "react-loader-spinner"

const StartingSite = React.lazy(()=>import("./pages/StartingSite"))
const RepertuarSite = React.lazy(()=>import("./pages/RepertuarSite"))
const CinemaHallSite = React.lazy(()=>import("./pages/CinemaHallSite"));
const ProfileSite = React.lazy(()=>import("./pages/ProfileSite"));

const App = () =>{
    const style = { position: "fixed", top: "50%", left: "50%", transform: "translate(-50%, -50%)" };


    return (
        <div>
            <Layout>
                <Suspense fallback={<div style={style}><Loader type="Plane" color="8b0000" secondaryColor="white"/></div>}>
                    <Routes>
                        <Route path="/" element={<StartingSite/>}/>
                        <Route path="/repertuar" element={<RepertuarSite/>}/>
                        <Route path="/rezerwacja" element={<CinemaHallSite/>}/>
                        <Route path="/profil" element={<ProfileSite/>}/>
                        <Route path="*" element={<Navigate to="/"/>}/>
                    </Routes>
                </Suspense>
            </Layout>
        </div>
    )
}

export default App;
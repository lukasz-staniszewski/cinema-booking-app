import './App.css';
import {Routes, Route, Navigate} from "react-router-dom";
import Layout from "./components/Layout";
import React, {Suspense} from "react";
import LoadingSpinner from "./components/LoadingSpinner";

const StartingSite = React.lazy(()=>import("./pages/StartingSite"))
const RepertuarSite = React.lazy(()=>import("./pages/RepertuarSite"))

const App = () =>{
    return (
        <div>
            <Layout>
                <Suspense fallback={<LoadingSpinner/>}>
                    <Routes>
                        <Route path="/" element={<StartingSite/>}/>
                        <Route path="/repertuar" element={<RepertuarSite/>}/>
                        <Route path="*" element={<Navigate to="/"/>}/>
                    </Routes>
                </Suspense>
            </Layout>
        </div>
    )
}

export default App;
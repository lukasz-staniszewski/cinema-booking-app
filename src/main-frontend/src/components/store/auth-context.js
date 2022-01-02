import React, {useCallback, useState} from "react";

const AuthContext = React.createContext({
    token: '',
    isUserLogged: false,
    login: (token) => {},
    logout: () => {},
});

export const AuthContextProvider = (props) => {
    const tokenData = localStorage.getItem('token');

    let initialToken;
    if (tokenData) {
        initialToken = tokenData.token;
    }

    const [token, setToken] = useState(initialToken);

    const isUserLoggedIn = !!token;
    const logoutHandler = useCallback(()=>{
        setToken(null);
        localStorage.removeItem('token');
    }, []);

    const loginHandler = (token)=>{
        setToken(token);
        localStorage.setItem('token', token);
    };

    const contextValue = {
        token: token,
        isUserLogged: isUserLoggedIn,
        login: loginHandler,
        logout: logoutHandler,
    };

    return (
        <AuthContext.Provider value = {contextValue}>
            {props.children}
        </AuthContext.Provider>
    )
}

export default AuthContext;
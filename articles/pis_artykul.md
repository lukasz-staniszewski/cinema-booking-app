<h1 align="center">NIE TAKI REACT STRASZNY JAK GO MALUJĄ</h1>
<h3 align="center">Łukasz Staniszewski, Politechnika Warszawska</h3>

<div align="center">
<img src="https://media1.giphy.com/media/eNAsjO55tPbgaor7ma/giphy.gif" alt="banner">

</div>

## Spis treści
* [Wprowadzenie](#wprowadzenie)
* [Facebook a React](#facebook_react)
* [Od czego zacząć?](#start)
* [Podsumowanie](#podsumowanie)
* [Bibliografia](#bibliografia)


## Wprowadzenie
W tym artykule chciałbym przybliżyć Wam czym jest React, a także przedstawić moją pierwszą przygodę z <del>tym frameworkiem</del> tą biblioteką języka JavaScript. Artykuł ten piszę, aby zachęcić Was do poznania tego potężnego rozwiązania, a także przedstawić porady, którymi możecie się kierować w trakcie jego poznawania we własnym zakresie. Dodatkowo, chciałbym opowiedzieć o wykorzystanu tego narzędzia w realizowanej części frontendowej projektu, wykonywanego przez mój zespół jako aplikacja webowa do rezerwacji biletów w kinie.

Zanim przejdę do meritum tego artykułu, warto by było wspomnieć, czemu zdecydowałem się na użycie właśnie Reacta do realizacji warstwy frontendowej wcześniej wymienionego projektu. Odpowiedź mogłaby nasunąć się sama - popularność, liczba ofert pracy na rynku, liczba tutoriali czy nawet wielkość społeczności wokół tego rozwiązania - każda z tych cech na pewno stawia w dobrym świetle obecną sytuację tej biblioteki (która niezmiennie dominuje rynek rozwiązań frontendowych od 2016 roku). 

<br>

<div align="center">
<img src="https://tsh.io/wp-content/uploads/2020/08/State-of-Frontend-2020-Vue.js-Svelte_.png" alt="popularnosc frontend">
</div>

<br>

Jednak to nie możliwości rynkowe były tym, co skłoniło Facebooka do zainwestowania w tą technologię - wkońcu to Facebook wypromował Reacta i Facebookowi React może w dużej części zawdzięczać swoją obecną sytuację. 


## Facebook a React <a name="facebook_react"></a>
Gdy Facebook wykupił Instagrama w 2012 roku, Instagram był jedynie zwykłą aplikacją - potrzebował on dobrze zrealizowanej aplikacji Webowej, która mogłaby otworzyć furtkę do ekspansji popularności Instagrama na cały świat.

Do zrealizowania tej aplikacji zgodnie z założeniami firmy, Facebook potrzebował biblioteki JavaScriptowej - po wykonaniu odpowiedniego researchu, realizator projektu wybrał właśnie Reacta, a swój wybór uargumentował w następujący sposób:

>“Napisaliśmy prototypowy profil użytkownika Instagrama w Reactcie i wyglądał bardzo dobrze. Powstał szybko, działał naprawdę dobrze i nie miał zbyt wielu bugów”

React sprawdził się przy realizacji prostej rzeczy, dlatego zespół zdecydował się na niego przy realizacji kolejnych funkcjonalności. Z czasem procent wykorzystania Reacta w "facebookowym" rozwiązaniu rósł. Zespół rozwijający był bardzo zadwolony z Reacta, ponieważ spełniał on oczekiwania, a jednocześnie w bardzo szybki i prosty sposób potrafił tworzyć apliakcje, które działały na komputerach milionów użytkowników na całym świecie.

## Od czego zacząć?
Teraz chciałbym przybliżyć Wam, jakie elementy Reacta są niezbęde do odkrycia w trakcie procesu zapoznawania się z Reactem. Można je traktować jako odpowiedź na pytania takie jak: <i>od czego zacząć?, jak rozplanować swoją naukę?, co jest wymagane przy realizacji pierwszego projektu?</i>

Takie zestawienie, nie dość, że może pokazać Wam drogę jaką obrałem przy uczeniu się tej biblioteki, to też może ułatwić zdecydowanie, co tak naprawde musicie umieć, zanim udacie się na swoją pierwszą rozmowę kwalifikacyjną na stanowisko Junior React Developer.

Tak więc, planując naukę Reacta warto skupić się na początku na następujących koncepcjach tej biblioteki:

<h3 align="center"><u>Komponenty funkcyjne / klasowe</u> </h3>

<div align="center">
<img src="https://miro.medium.com/max/1400/1*uVjpZGVI-a8MPHaJCP8uXw.jpeg" alt="klasowe vs funkcyjne">
</div>

<br>
Są to niezależne części interfejsu użytkownika, które mogą być wielokrotnie wykorzystane. Najprostszy komponent można zapisać jako funkcję, która zwraca JSX - rozszerzenie składni JavaScript o możliwość dodawania znaczników HTML. Poniżej zamieszczam przykład komponentu funkcyjnego z wykorzystaniem funkcji strzałkowej, która została wprowadzana wraz ze standardem ES6 do Reacta:

<br>

```js
const Component = () => {
    const welcomeText=<h1>Pierwszy komponent</h1>;

    return (
        <div>
            {welcomeText}
            <button>Start</button>
        </div>
    )
}
```

W React możemy spotkać się również z komponentami typu klasowego. Jest to obecnie dużo rzadziej stosowana koncepcja niż komponenty funkcyjne.  Dla porównania, przedstawiony wcześniej komponent funkcyjny jest równoznaczny takiemu zapisowi w postaci klasy:
```js
class Component extends React.Component {
    render() {
        const welcomeText=<h1>Pierwszy komponent</h1>;
        return (
            <div>
                {welcomeText}
                <button>Start</button>
            </div>
        ) 
    } 
}
```
Komponenty klasowe mogą być uciążliwe w trakcie pisania przez nas kodu - nie oferują one m.in. eleganckiego rozwiązania współdzielenia logiki między komponentami. Dodatkowo, dzięki komponentom funkcyjnym z hookami, udostępnianie i ponowne używanie komponentów jest znacznie łatwiejsze. Jak można z tych dwóch zdań wywnioskować - komponenty klasowe są już coraz rzadziej spotykanym rozwiązaniem w aplikacjach Reactowych, jednak biblioteka wciąż udostępnia (i udostępniać będzie) możliwość łączenia obu rozwiązań ze sobą w kodzie, tak więc używanie każdego z nich jest poprawne.

<h3 align="center"> <u>Hooki </u></h3>

<div align="center">
<img src="https://user-images.githubusercontent.com/59453698/149218428-909dfb9c-c680-43cd-a857-19d5883a57dd.png" alt="react hook">
</div>

Nie dość, że już raz zostało w tym artykule wspomniane to słowo, prawdopodobnie chociaż raz zdarzyło Wam się usłyszeć te wyrażenie, gdy była mowa o Reactcie. Warto więc teraz odpowiedzieć na zasadnicze pytanie - czym tak naprawdę są hooki? Najłatwiej odpowiedzieć, że są to określone fragmenty logiki zawierającej stan, które są zapisane w postaci funkcji. 

Hooki mogą być wykorzystane w wielu komponentach, a nawet wielokrotnie w obszarze tylko jednego. Oficjalna dokumentacja Reacta zachęca do  skorzystania z tego rozwiązania w swoim kodzie.

 Warto zaznaczyć, że hooki mogą być używane tylko wewnątrz komponentów funkcyjnych - nie jest to jednak problem w przypadku pracy z już istniejącą aplikacją korzystającą z komponentów wersji klasowej, ponieważ można mieszać komponenty funkcyjne i klasowe wewnątrz aplikacji. 
 
 Do najbardziej podstawowych hooków można zakwalifikować [useState](https://pl.reactjs.org/docs/hooks-state.html), [useEffect](https://pl.reactjs.org/docs/hooks-effect.html) czy [useContext](https://www.youtube.com/watch?v=5LrDIWkK_Bc) - zachęcam Was do zajrzenia pod wskazane linki i poczytania, bo jest to bardzo przydatna wiedza.
 
 Przykład zastosowania hooka useState, który pozwala korzystać ze stanu countera (początkowo inicjowanego na 0), w taki sposób, że licznik zwiększa się o 1 za każdym razem, gdy wciśniemy przycisk:
```js
import React, { useState } from 'react';

const Component = () => {
    const [counter, setCounter] = useState(0);

    const changeCounterHandler = () => {
        setCounter(counter+1);
    }

    return (
        <div>
            <p>Aktualna wartosc licznika: {counter}</p>
            <button type="button" onClick={changeCounterHandler}>Dodaj</button>
        </div>
    )
}
```

<h3 align="center"> <u>Renderowanie warunkowe </u></h3>

Dzięki temu rozwiązaniu renderowanie może zostać wykonane z zastosowaniem przykładowo instrukcji warunkowej if. W zależności od stanu aplikacji, możemy przedstawić odpowiednie rozwiązanie w obszarze tego samego komponentu, co najłatwiej zobrazować przykładem. 

W przypadku realizowanego przez mój zespół projektu Rezerwacji Biletów w Kinie, stworzony został system, gdzie użytkownicy, po zalogowaniu się, mogą wejść w panel swojego konta i tam anulować wykonaną wcześniej rezerwację. Dobrze by było nie umożliwiać użytkownikom wejście w swój profil, gdy nie są oni zalogowani - do tego można właśnie wykorzystać renderowanie warunkowe i nie wyświetlać opcji wejścia w profil, gdy użytkownik nie jest zalogowany.

W przypadku tego projektu, można zrealizować taką logikę przy użyciu jednej linijki, która mówi mniej więcej tyle - "jeśli ``auchCtx.isUserLogged`` jest prawdziwe, pokaż pole profil w menu, w innym przypadku zachowuj się jakby takiego pola nigdy nie było": 

```js
{authCtx.isUserLogged && <li><NavLink to={"/profil"}>Profil</NavLink></li>}
```

A tak wygląda efekt na stronie:
<div align="center">

![cinema-booking-app](https://user-images.githubusercontent.com/59453698/149200030-a76b6b1d-f231-4a9e-b146-519c68a6d6dd.gif)
</div>

<h3 align="center"> <u>React Router</u> </h3>

<div align="center">

![ezgif-2-0feaf28a8a](https://miro.medium.com/max/1200/1*TKvlTeNqtkp1s-eVB5Hrvg@2x.png)
</div>

<br>
React Router to rozwiązanie, które umożliwia tworzenie tzw. Single Page Apllication czyli aplikacji korzystającej z tylko jednego pliku HTML, dzięki czemu zapobiega przeładowywaniu kolejnych podstron, a wygląd interfejsu zmienia się dzięki asynchronicznemu ładowaniu odpowiednich elementów.

<br>
W kodzie, implementacja, jak ze wszystkim w Reactcie, sprowadza się do paru linii, gdzie zapisujemy kolejne ścieżki (ang. routes), przeprowadzające do konkretnych stron (zaimplementowanych jako komponenty):

<br>

```js
<Routes>
    <Route path="/" element={<StartingSite/>}/>
    <Route path="/repertuar" element={<RepertuarSite/>}/>
    <Route path="/rezerwacja" element={<CinemaHallSite/>}/>
    <Route path="*" element={<Navigate to="/"/>}/>
</Routes>
```




## Podsumowanie
Mam nadzieję, że ten krótki artykuł przybliżył Wam trochę tą potężną bibliotekę JavaScript, jaką jest React. To co było celem artykułu, to pokazanie nie tyle Reacta w samych superlatywach i świetle najlepszego rozwiązania na świecie, a to, jak proste może być pisanie Frontendu aplikacji webowych przy jego użyciu. React, moim zdaniem, jest bardzo uniwersalnym i dobrym kursem, który możesz obrać (o ile rozumiesz podstawy HTML czy CSS), gdy chcesz rozwinąć się w zakresie projektowania aplikacji od strony interfejsu użytkownika.

<div align="Center">
Dzięki za uwagę!
</div>

## Bibliografia
+ https://geek.justjoin.it/powstal-react-historia-trzech-developerow-ktorzy-wyszli-poza-schemat
+ https://tsh.io/blog/frontend-technologies-in-2020-state-of-frontend-report/
+ https://pl.reactjs.org/docs/getting-started.html
+ https://mansfeld.pl/webdesign/projektowanie-spa-single-page-app/
+ https://betterprogramming.pub/react-class-vs-functional-components-2327c7324bdd
+ https://www.web-developer-studio.pl/react-contextapi-w-praktyce/
+ https://www.telerik.com/blogs/understand-react-context-api
+ https://www.magicweb.pl/programowanie/frontend/react/context-api-uzycie-prop-drilling/
+ https://react-redux.js.org/introduction/why-use-react-redux
+ https://www.nafrontendzie.pl/podstawy-redux-zarzadzanie-stanem-react
+ https://typeofweb.com/react-hooks-wprowadzenie-i-motywacja
+ https://bulldogjob.pl/news/1273-pobieranie-danych-w-react-za-pomoca-hookow?fbclid=IwAR2tvZRo6S58wdpDWmZ1-2LYFcGPD3jlal48-kLEsgfYRnvrFtDc6evhqr0
+ https://velopert.com/2937
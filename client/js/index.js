import Header from './components/Header';
import Footer from './components/Footer';
import Home from './components/Home';
import crud from './crud/crud';
import HashTags from './components/HashTags';
import Lobsters from './components/Lobsters';
import Lobster from './components/Lobster';

buildPage();

function buildPage(){
    header();
    footer();
    navHome();
    navHashTags();
    navLobsters();
}

function header(){
    const headerElem = document.querySelector('.header');
    headerElem.innerHTML = Header();
   
}

function footer(){
    const footerElem = document.querySelector('.footer');
    footerElem.innerHTML = Footer();
}

function navHome(){
    const homeElem = document.querySelector('.nav-list__home');
    homeElem.addEventListener('click', () => {
        const app = document.querySelector('#app');
        app.innerHTML = Home ();
    });
}

function navHashTags(){
    const hashTagsElem = document.querySelector('.nav-list__hashtags');
    hashTagsElem.addEventListener('click', () =>{
        const app = document.querySelector('#app')
        crud.getRequest('http://localhost:8080/api/hashtags', hashTags => {
            app.innerHTML = HashTags(hashTags);
        });
    });

}

function navLobsters(){
    const lobsterElem = document.querySelector('.nav-list__lobsters');
    lobsterElem.addEventListener('click', () =>{
        const app = document.querySelector('#app');
        crud.getRequest('http://localhost:8080/api/lobsters', lobsters => {
            app.innerHTML = Lobsters(lobsters);
        });
        renderLobsterInfo();
    });
}

function renderLobsterInfo(){
    const app = document.querySelector('#app');
    app.addEventListener('click', () => {
      if (event.target.classList.contains('lobsters-list__name')) {
         const lobsterId = event.target.querySelector('#lobsterId').value;
         crud.getRequest(`http://localhost:8080/api/lobsters/${lobsterId}`, lobster => {
             app.innerHTML = Lobster(lobster);
         });
      }
    });
}


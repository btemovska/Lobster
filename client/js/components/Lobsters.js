export default function Lobsters(lobsters){
    return `
    <h1>Lobsters list:</h1>
    <ul class="lobster-list">
        ${lobsters.map(lobster => {
            return `
            <li class="lobsters-list__name">${lobster.name}
                <input type="hidden" id="lobsterId" value="${lobster.id}">    
            </li>
            `;
        }).join('')}
    </ul>
    `;
}
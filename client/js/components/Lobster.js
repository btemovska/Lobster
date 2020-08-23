export default function Lobster(lobster) {
    return `
    <h1>${lobster.name}</h1>
    <br>
    <h2>Description:</h2>
    <br>
    <h2>${lobster.description}</h2>
    <ul>
    ${lobster.hashTags.map(hashTag => {
        return `
        <li>
            <span>${hashTag.name}</span>        
        </li>
        `;
    }).join('')}
    </ul>
    `;
}
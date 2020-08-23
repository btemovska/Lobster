export default function HashTags(hashTags){
    return `
    <h1>Saved HashTags</h1>
    <ul class="hashTags-list">
        ${hashTags.map(hashTag => {
            return `
            <li class="hashTags-list__name">${hashTag.name}</li>
            `;
        }).join('')}
    </ul>
    `;
}
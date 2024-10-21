export interface Specie {
    id: string;
    name: string;
    occurring: Continents; 
    dietType: DietType;     
}


export enum Continents {
    NORTH_AMERICA = 'NORTH_AMERICA',
    SOUTH_AMERICA = 'SOUTH_AMERICA',
    EUROPE = 'EUROPE',
    ASIA = 'ASIA',
    AFRICA = 'AFRICA',
    AUSTRALIA = 'AUSTRALIA',
    ANTARCTIC = 'ANTARCTIC',
    ARCTIC = 'ARCTIC'
}

export enum DietType {
    CARNIVORE = 'CARNIVORE',
    HERBIVORE = 'HERBIVORE',
    OMNIVORE = 'OMNIVORE',
    INSECTIVORE = 'INSECTIVORE',
    DETRIVORE = 'DETRIVORE'
}

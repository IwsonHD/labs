import { Specie } from '../../specie/model/specie';

export interface Animal {
    id: string;
    specie_id: string;
    age: number;
    weight: number;     
}
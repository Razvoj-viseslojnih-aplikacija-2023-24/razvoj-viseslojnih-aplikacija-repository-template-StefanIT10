import { Sud } from "./sud";

export class Predmet{
    id!: number;
    brojPr!: string;
    opis!: string;
    datumPocetka!: Date;
    aktivan!: boolean;
    sud!: Sud;
}
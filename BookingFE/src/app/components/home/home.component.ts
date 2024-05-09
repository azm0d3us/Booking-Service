import { Component } from '@angular/core';
import { CrossTestService } from '../../services/cross-test.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

  urls!: string[];
  images: string[] = [];

  //images = [944, 1011, 984].map((n) => `https://picsum.photos/id/${n}/900/500`);

  constructor(private crossService: CrossTestService, ) {
  }

  ngOnInit(): void {
    this.urls = this.crossService.getUrls();
    console.log("SOMETHING");
    // console.log(this.urls);
    this.test(this.urls).then(() => {
      console.log("Test completed");
    }).catch(error => {
      console.error("Errore nella funzione test(): ", error);
    });
  }

  test(array: any[]): Promise<void> {
    return new Promise<void>((resolve, reject) => {
      array.forEach(url => {
        console.log(url);
        console.log("CIaoooooo");
        // Puoi eseguire altre operazioni con l'url qui
      });

      resolve(); // Risolvi la promessa una volta completato il ciclo forEach
    });
  }
}

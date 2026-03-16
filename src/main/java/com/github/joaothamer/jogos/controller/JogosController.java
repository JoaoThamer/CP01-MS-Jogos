package com.github.joaothamer.jogos.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jogos")
public class JogosController {

    @GetMapping("/mario")
    public String mario(){

        String aux = "                                                                      \uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\n" +
                "                                                                 \uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\n" +
                "                                                                 \uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFE7\n" +
                "                                                            \uD83D\uDFEB\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFEB\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\n" +
                "                                                            \uD83D\uDFEB\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFEB\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\n" +
                "                                                            \uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\n" +
                "                                                                           \uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\n" +
                "                                                                 \uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFE5\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\n" +
                "                                                            \uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFE5\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFE5\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\n" +
                "                                                       \uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\n" +
                "                                                       \uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFEB\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE7\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE7\uD83D\uDFE5\uD83D\uDFEB\uD83D\uDFE7\uD83D\uDFE7\n" +
                "                                                       \uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\n" +
                "                                                       \uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE7\uD83D\uDFE7\n" +
                "                                                                      \uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5          \uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\n" +
                "                                                                 \uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB               \uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\n" +
                "                                                            \uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB               \uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB\uD83D\uDFEB            ";
        return "Super Mario Galaxy.\n" + aux;
    }

    @GetMapping("/zelda")
    public String zelda(){
        String aux =
                "⢦⣤⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀  ⠀⣼⣆⠀⠀⠀⠀⠀⠀⠀⠀   ⠀⠀⠀⣀⣠⡤\n" +
                "⠘⣿⣿⣿⣷⣦⣄⣀⠀⢠⠔⠀⢀⡼⠿⠿⢆⠀⠀⠲⣄⠀⣀⣠⣴⣾⣿⣿⣿⠇\n" +
                "⠀⠈⠉⠉⠛⠛⠻⠿⢿⣿⠀⢀⣾⣷⡀⢀⣾⣷⡀⠀⣿⡿⠿⠿⠛⠛⠉⠉⠁⠀\n" +
                "⠀⠀⣤⣤⣶⣶⣶⣶⣶⣿⣆⠈⠉⠉⠉⠉⠉⠉⠉⢠⣿⣶⣶⣶⣶⣶⣤⣤⠀⠀\n" +
                "⠀⠀⠘⣿⡿⠟⠛⠉⣡⣿⣿⣷⣤⠀⢠⣆⠀⣤⣶⣿⣿⣬⡉⠛⠻⠿⣿⠇⠀⠀\n" +
                "⠀⠀⠀⠀⠀⢀⣴⣿⡿⢋⣿⣿⠛⢠⣿⣿⡄⠛⢿⣿⡘⢿⣿⣦⣀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠉⠻⠏⠀⣸⣿⡇⢀⠻⣿⣿⠟⣀⠸⣿⣇⠀⠙⠟⠋⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢠⡟⠁⣿⣿⠀⠻⣆⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠘⢟⠉⠙⠓⠀⠘⠏⠀⠘⠟⠉⡻⠋⠀⠀⠀⠀⠀⠀⠀⠀";
        return "The Legends of Zelda Breath of the wild.\n" + aux;
    }

}

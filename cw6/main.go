package main

import (
	"bytes"
	"encoding/json"
	"fmt"
	"io"
	"io/ioutil"
	"log"
	"net/http"
	"os"
	"strings"
)

const uri string = "http://localhost:8098/buckets/secondtask/keys/"
const file_path string = "./wyniki.txt"

func main() {
	games := readGames("./games.json")
	insertToRiak(games)
	fmt.Println("----------------------")
	for _, game := range games {
		readFromRiak(game.Key)
	}
	fmt.Println("----------------------")
	nGame := Game{
		MetacriticRating: 10.0,
	}
	gameToUpdate := games[2]
	updateRiak(gameToUpdate.Key, nGame)
	fmt.Println("----------------------")
	readFromRiak(gameToUpdate.Key)
	fmt.Println("----------------------")
	gameToDelete := games[1]
	deleteFromRiak(gameToDelete.Key)
	fmt.Println("----------------------")
	readFromRiak(gameToDelete.Key)
}

type Game struct {
	Title            string  `json:"title"`
	YearOfRelease    int     `json:"year_of_release"`
	MetacriticRating float32 `json:"metacritic_rating"`
	GameOfTheYear    bool    `json:"game_of_the_year"`
	Key              string  `json:"-"`
}

func readGames(path string) []*Game {
	file, err := ioutil.ReadFile(path)
	if err != nil {
		log.Fatalln("Error while reading json file, exiting")
	}
	var games []*Game

	err = json.Unmarshal([]byte(file), &games)
	if err != nil {
		log.Fatalln(err)
	}
	for _, game := range games {
		game.Key = strings.ReplaceAll(strings.ToLower(game.Title), " ", "_")
	}
	return games
}

func writeToResultFile(path string, header http.Header, body string, stage string) {
	file, err := os.OpenFile(path, os.O_APPEND|os.O_CREATE, 0644)
	if err != nil {
		log.Fatalln(err)
	}
	defer file.Close()
	headerStr := fmt.Sprintf("header: %v\n", header)
	_, err = io.WriteString(file, fmt.Sprintf("----%s----\n", stage))
	if err != nil {
		log.Fatalln(err)
	}
	_, err = io.WriteString(file, headerStr)
	if err != nil {
		log.Fatalln(err)
	}
	_, err = io.WriteString(file, body)
	if err != nil {
		log.Fatalln(err)
	}
}

func insertToRiak(games []*Game) {
	for _, game := range games {
		upsertRecordToRiak(*game, game.Key, "Insert")
	}
}

func upsertRecordToRiak(game Game, key string, stage string) {
	client := &http.Client{}
	json, err := json.Marshal(game)
	if err != nil {
		log.Fatalln(err)
	}
	req, err := http.NewRequest(http.MethodPut, uri+key, bytes.NewBuffer(json))
	if err != nil {
		log.Fatalln(err)
	}
	req.Header.Set("Content-Type", "application/json; charset=utf-8")
	resp, err := client.Do(req)
	if err != nil {
		log.Fatalln(err)
	}
	body := bodyToString(resp.Body)
	writeToResultFile(file_path, resp.Header, body, stage)
}

func readFromRiak(key string) {
	resp, err := http.Get(uri + key)
	if err != nil {
		log.Fatalln(err)
	}
	fmt.Printf("Headers: %v\n", resp.Header)
	body := bodyToString(resp.Body)
	fmt.Println(body)
	writeToResultFile(file_path, resp.Header, body, "Read")
}

func updateRiak(key string, nGame Game) {
	upsertRecordToRiak(nGame, key, "Update")
}

func bodyToString(body io.ReadCloser) string {
	b, err := io.ReadAll(body)
	if err != nil {
		log.Fatalln(err)
	}
	return fmt.Sprintf("Body: %s\n", string(b))
}

func deleteFromRiak(key string) {
	client := &http.Client{}
	req, err := http.NewRequest(http.MethodDelete, uri+key, nil)
	if err != nil {
		log.Fatalln(err)
	}
	resp, err := client.Do(req)
	if err != nil {
		log.Fatalln(err)
	}
	body := bodyToString(resp.Body)
	fmt.Println(body)
	writeToResultFile(file_path, resp.Header, body, "Delete")
}

package examples

import (
	"encoding/json"
	"github.com/spf13/viper"
	"log"
	"os"
	log2 "yatori-go-core/utils/log"
)

type JSONDataForConfig struct {
	Setting Setting `json:"setting"`
	Users   []Users `json:"users"`
}
type EmailInform struct {
	Sw       int    `json:"sw"`
	SMTPHost string `json:"smtpHost"`
	SMTPPort string `json:"smtpPort"`
	Email    string `json:"email"`
	Password string `json:"password"`
}
type BasicSetting struct {
	CompletionTone int    `default:"1" json:"completionTone,omitempty"` //是否开启刷完提示音，0为关闭，1为开启，默认为1
	ColorLog       int    `json:"colorLog,omitempty"`                   //是否为彩色日志，0为关闭彩色日志，1为开启，默认为1
	LogOutFileSw   int    `json:"logOutFileSw,omitempty"`               //是否输出日志文件0代表不输出，1代表输出，默认为1
	LogLevel       string `json:"logLevel,omitempty"`                   //日志等级，默认INFO，DEBUG为找BUG调式用的，日志内容较详细，默认为INFO
	LogModel       int    `json:"logModel"`                             //日志模式，0代表以视屏提交学时基准打印日志，1代表以一个课程为基准打印信息，默认为0
}
type AiSetting struct {
	AiType string `json:"aiType"`
	APIKEY string `json:"API_KEY" yaml:"API_KEY" mapstructure:"API_KEY"`
}
type Setting struct {
	BasicSetting BasicSetting `json:"basicSetting"`
	EmailInform  EmailInform  `json:"emailInform"`
	AiSetting    AiSetting    `json:"aiSetting"`
}
type CoursesSettings struct {
	Name         string   `json:"name"`
	IncludeExams []string `json:"includeExams"`
	ExcludeExams []string `json:"excludeExams"`
}
type CoursesCustom struct {
	VideoModel      int               `json:"videoModel"`
	AutoExam        int               `json:"autoExam"`
	ExcludeCourses  []string          `json:"excludeCourses"`
	IncludeCourses  []string          `json:"includeCourses"`
	CoursesSettings []CoursesSettings `json:"coursesSettings"`
}
type Users struct {
	AccountType   string        `json:"accountType"`
	URL           string        `json:"url"`
	Account       string        `json:"account"`
	Password      string        `json:"password"`
	CoursesCustom CoursesCustom `json:"coursesCustom"`
}

// 读取json配置文件
func ReadJsonConfig(filePath string) JSONDataForConfig {
	var configJson JSONDataForConfig
	content, err := os.ReadFile(filePath)
	if err != nil {
		log.Fatal(err)
	}
	err = json.Unmarshal(content, &configJson)
	if err != nil {
		log.Fatal(err)
	}
	return configJson
}

// 自动识别读取配置文件
func ReadConfig(filePath string) JSONDataForConfig {
	var configJson JSONDataForConfig
	viper.SetConfigName("TestData")
	viper.SetConfigType("yaml")
	viper.AddConfigPath(filePath)
	err := viper.ReadInConfig()
	if err != nil {
		log2.Print(log2.INFO, log2.BoldRed, "找不到配置文件")
		log.Fatal("")
	}
	err = viper.Unmarshal(&configJson)
	//viper.SetTypeByDefaultValue(true)
	viper.SetDefault("setting.basicSetting.logModel", 5)

	if err != nil {
		log2.Print(log2.INFO, log2.BoldRed, "配置文件读取失败，请检查配置文件填写是否正确")
		log.Fatal(err)
	}
	return configJson
}

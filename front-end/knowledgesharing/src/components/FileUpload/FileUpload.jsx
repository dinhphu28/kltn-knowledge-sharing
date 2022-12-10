import React, { Component } from "react";
import uploadFileApi from "../../apis/uploadFileApi";


export default class UploadFiles extends Component {
  constructor(props) {
    super(props);

    this.selectFile = this.selectFile.bind(this);
    this.upload = this.upload.bind(this);

    this.state = {
      selectedFiles: undefined,
      currentFile: undefined,
      progress: 0,
      message: ""
    };
  }

  selectFile(event) {
    this.setState({
      selectedFiles: event.target.files,
    });
  }

  upload() {
    let currentFile = this.state.selectedFiles[0];

    this.setState({
      progress: 0,
      currentFile: currentFile,
    });

    uploadFileApi.upload(currentFile, (event) => {
      this.setState({
        progress: Math.round((100 * event.loaded) / event.total),
      });
    })
      .then((response) => {
        this.setState({
          message: response.data.fileName,
        });

        this.props.onHandleChange(response.data.fileName);

        return response.data;
      })
      // .then((files) => {
      //   this.setState({
      //     fileInfos: files.data,
      //   });
      // })
      .catch(() => {
        this.setState({
          progress: 0,
          message: "Could not upload the file!",
          currentFile: undefined,
        });
      });

    this.setState({
      selectedFiles: undefined,
    });
  }

  // componentDidMount() {
  //   uploadFileApi.getFiles().then((response) => {
  //     this.setState({
  //       fileInfos: response.data,
  //     });
  //   });
  // }

  render() {
    const { selectedFiles, currentFile, progress, message } =
      this.state;

    return (
      <div>
        {currentFile && (
          <div className="progress">
            <div
              className="progress-bar progress-bar-info progress-bar-striped"
              role="progressbar"
              aria-valuenow={progress}
              aria-valuemin="0"
              aria-valuemax="100"
              style={{ width: progress + "%" }}
            >
              {progress}%
            </div>
          </div>
        )}

        <label className="btn btn-default">
            <input type="file" onChange={this.selectFile} />
        </label>
        <button className="btn btn-success"
          disabled={!selectedFiles}
          onClick={this.upload}
        >
          Upload
        </button>
        <div className="alert alert-light" role="alert">
          {message}
        </div>
      </div>
    );
  }
}
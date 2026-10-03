import { staticFile } from "remotion";

export type Shot = {
  type: "image" | "video";
  src: string;
  width: number;
  height: number;
  /** How long this shot is on screen, including the crossfade overlap with its neighbours. */
  durationInFrames: number;
  /** For video: which second to start playing from. */
  startFromSeconds?: number;
  /** Ken Burns pan direction for images. */
  pan?: "in" | "out" | "left" | "right";
  caption?: string;
};

const PHOTO_DURATION = 62; // ~2.06s @ 30fps
const LANDSCAPE_DURATION = 70; // ~2.3s, a little longer to enjoy the wide shots

export const SHOTS: Shot[] = [
  {
    type: "image",
    src: staticFile("PXL_20220914_234612578.jpg"),
    width: 2268,
    height: 4032,
    durationInFrames: PHOTO_DURATION,
    pan: "in",
    caption: "the morning of",
  },
  {
    type: "video",
    src: staticFile("input.MOV"),
    width: 2160,
    height: 3840,
    durationInFrames: 150,
    startFromSeconds: 0,
    caption: "on the road",
  },
  {
    type: "image",
    src: staticFile("PXL_20220914_210458662.MP.jpg"),
    width: 4032,
    height: 2268,
    durationInFrames: LANDSCAPE_DURATION,
    pan: "left",
    caption: "Glass House Mountains National Park",
  },
  {
    type: "image",
    src: staticFile("PXL_20220914_192344511.MP.jpg"),
    width: 2268,
    height: 4032,
    durationInFrames: PHOTO_DURATION,
    pan: "out",
  },
  {
    type: "image",
    src: staticFile("PXL_20220914_192346186.MP.jpg"),
    width: 2268,
    height: 4032,
    durationInFrames: PHOTO_DURATION,
    pan: "in",
    caption: "moonrise over the ranges",
  },
  {
    type: "image",
    src: staticFile("PXL_20220914_194359284.MP.jpg"),
    width: 2268,
    height: 4032,
    durationInFrames: PHOTO_DURATION,
    pan: "left",
  },
  {
    type: "image",
    src: staticFile("PXL_20220914_194400721.MP.jpg"),
    width: 2268,
    height: 4032,
    durationInFrames: PHOTO_DURATION,
    pan: "right",
  },
  {
    type: "image",
    src: staticFile("PXL_20220914_194402345.MP.jpg"),
    width: 2268,
    height: 4032,
    durationInFrames: PHOTO_DURATION,
    pan: "in",
  },
  {
    type: "image",
    src: staticFile("PXL_20220914_194404320.MP.jpg"),
    width: 2268,
    height: 4032,
    durationInFrames: PHOTO_DURATION,
    pan: "out",
  },
  {
    type: "image",
    src: staticFile("PXL_20220914_194419557.MP.jpg"),
    width: 2268,
    height: 4032,
    durationInFrames: PHOTO_DURATION,
    pan: "left",
  },
  {
    type: "image",
    src: staticFile("PXL_20220914_194436895.MP.jpg"),
    width: 4032,
    height: 2268,
    durationInFrames: LANDSCAPE_DURATION,
    pan: "right",
  },
  {
    type: "image",
    src: staticFile("PXL_20220914_194439963.MP.jpg"),
    width: 4032,
    height: 2268,
    durationInFrames: LANDSCAPE_DURATION,
    pan: "in",
  },
  {
    type: "image",
    src: staticFile("PXL_20220914_194442437.MP.jpg"),
    width: 4032,
    height: 2268,
    durationInFrames: LANDSCAPE_DURATION,
    pan: "out",
  },
  {
    type: "video",
    src: staticFile("PXL_20220914_194510372.mp4"),
    width: 1920,
    height: 1080,
    durationInFrames: 210,
    startFromSeconds: 0,
    caption: "sundown from the summit",
  },
  {
    type: "image",
    src: staticFile("PXL_20220914_200603955.MP.jpg"),
    width: 2268,
    height: 4032,
    durationInFrames: PHOTO_DURATION,
    pan: "left",
  },
  {
    type: "image",
    src: staticFile("PXL_20220914_200612549.MP.jpg"),
    width: 1836,
    height: 3264,
    durationInFrames: PHOTO_DURATION,
    pan: "in",
  },
  {
    type: "image",
    src: staticFile("PXL_20220914_200714941.MP.jpg"),
    width: 2268,
    height: 4032,
    durationInFrames: PHOTO_DURATION,
    pan: "right",
  },
  {
    type: "image",
    src: staticFile("PXL_20220914_210513988.MP.jpg"),
    width: 2268,
    height: 4032,
    durationInFrames: PHOTO_DURATION,
    pan: "out",
  },
  {
    type: "image",
    src: staticFile("PXL_20220914_214233408.MP.jpg"),
    width: 4032,
    height: 2268,
    durationInFrames: LANDSCAPE_DURATION,
    pan: "left",
  },
  {
    type: "image",
    src: staticFile("PXL_20220914_214805242.MP.jpg"),
    width: 2268,
    height: 4032,
    durationInFrames: PHOTO_DURATION,
    pan: "in",
  },
  {
    type: "image",
    src: staticFile("PXL_20220914_214810885.MP.jpg"),
    width: 2268,
    height: 4032,
    durationInFrames: PHOTO_DURATION,
    pan: "out",
    caption: "nightfall",
  },
  {
    type: "image",
    src: staticFile("PXL_20220914_234620809.MP.jpg"),
    width: 2268,
    height: 4032,
    durationInFrames: PHOTO_DURATION,
    pan: "left",
  },
  {
    type: "image",
    src: staticFile("PXL_20220914_234630454.MP.jpg"),
    width: 2268,
    height: 4032,
    durationInFrames: PHOTO_DURATION,
    pan: "in",
  },
  {
    type: "image",
    src: staticFile("PXL_20220914_093000807.MP.jpg"),
    width: 4032,
    height: 2268,
    durationInFrames: LANDSCAPE_DURATION,
    pan: "right",
    caption: "til next time",
  },
  {
    type: "image",
    src: staticFile("PXL_20220914_093005165.MP.jpg"),
    width: 2268,
    height: 4032,
    durationInFrames: PHOTO_DURATION,
    pan: "out",
  },
];

class Solution:
    def separateSquares(self, squares: List[List[int]]) -> float:
        events = []

        for x, y, l in squares:
            events.append((y, 1, x, x + l))      
            events.append((y + l, -1, x, x + l)) 

        events.sort()
        active = []

        def union_width(intervals):
            intervals.sort()
            total = 0
            cur_s, cur_e = intervals[0]
            for s, e in intervals[1:]:
                if s > cur_e:
                    total += cur_e - cur_s
                    cur_s, cur_e = s, e
                else:
                    cur_e = max(cur_e, e)
            total += cur_e - cur_s
            return total

        segments = []
        prev_y = events[0][0]
        area = 0.0

        for y, typ, x1, x2 in events:
            if active and y > prev_y:
                width = union_width(active)
                seg_area = width * (y - prev_y)
                segments.append((prev_y, y, width, area))
                area += seg_area

            if typ == 1:
                active.append((x1, x2))
            else:
                active.remove((x1, x2))

            prev_y = y

        total_area = area
        half = total_area / 2.0

    
        for y1, y2, width, prefix in segments:
            seg_area = width * (y2 - y1)
            if prefix + seg_area >= half:
                return y1 + (half - prefix) / width

        return 0.0
